package com.example.shoppingapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;

public class Custom extends AppCompatActivity {

    private ListView listMode;
    private EditText editTextText;
    private ArrayList<Message> messages = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextText = (EditText) findViewById(R.id.editTextText);
        Button button = (Button) findViewById(R.id.button);
        listMode = (ListView)findViewById(R.id.listMode);

        //新增一個Adapter
        adapter = new MyAdapter(this, messages);
        listMode.setAdapter(adapter);

        button.setOnClickListener(v -> {
            String userMessage = editTextText.getText().toString();
            messages.add(new Message("user", userMessage));
            adapter.notifyDataSetChanged();
            editTextText.setText(""); // Clear the input field
            modelCall(userMessage);
        });

    }

    public void modelCall(String userMessage){
        GenerativeModel gm = new GenerativeModel("gemini-pro", "AIzaSyDItVGNqnXkVYsVNBbWypEtSSmwpBeFThU");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);
        Content content = new Content.Builder()
                .addText(userMessage)
                .build();

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String resultText = result.getText();
//                textView3.setText(resultText);
                messages.add(new Message("model", resultText));
                runOnUiThread(() -> adapter.notifyDataSetChanged());
//                System.out.println(resultText);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        },this.getMainExecutor());
    }
    class Message {
        String role;
        String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

    class MyAdapter extends ArrayAdapter<Message> {
        public MyAdapter(Context context, List<Message> messages) {
            super(context, 0, messages);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Message message = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_item, parent, false);
            }

            TextView messageTextView = convertView.findViewById(R.id.messageTextView);
            messageTextView.setText(message.content);

            if (message.role.equals("user")) {
                messageTextView.setTextColor(Color.GREEN);
            } else {
                messageTextView.setTextColor(Color.WHITE);
            }

            return convertView;
        }
    }
}