package connect.mta.matrp.gui.chatedgar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import connect.mta.matrp.R;
import connect.mta.matrp.gui.keyboard.KeyBoard;
import connect.mta.matrp.gui.util.CustomRecyclerView;
import connect.mta.matrp.gui.util.Utils;
//НА ОБНОВУ ТОЛЬКО РЕЛИЗ BY EDGAR 3.0
public class ChatManager {
    public Activity aactivity;
    private static ChatManager instance;
    public static int statusChat = 1;
    public FrameLayout chat;
    public CustomRecyclerView msg_messages;
    public ArrayList<String> msglist = new ArrayList<>();
    public ChatAdapter chatAdapter;
    public ImageView msg_box;
    public static boolean isChat = false;

    public native void sendChatMessages(byte[] messages);
//НА ОБНОВУ ТОЛЬКО РЕЛИЗ BY EDGAR 3.0
    public ChatManager(Activity activity){
        aactivity = activity;
        instance = this;
        chat = aactivity.findViewById(R.id.chat);
        msg_messages = aactivity.findViewById(R.id.msg_messages);
        msg_box = aactivity.findViewById(R.id.msg_box);

        msg_messages.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(aactivity, LinearLayoutManager.VERTICAL, false);
        msg_messages.setLayoutManager(layoutManager);

     //   msglist = Lists.msglist;
        chatAdapter = new ChatAdapter(aactivity, msglist);
        msg_messages.setAdapter(chatAdapter);
        msg_messages.setVerticalScrollBarEnabled(false);
        msg_messages.setEnableScrolling(false);
        msg_box.setAlpha(0.0f);

        Utils.ShowLayout(chat, false);
    }

    public final void ChatOpen(){
        msg_messages.setVerticalScrollBarEnabled(true);
        msg_messages.setEnableScrolling(true);
        if(statusChat == 3) {
            msg_messages.animate().alpha(1.0f).setDuration(300).start();
        }
        msg_box.animate().alpha(1.0f).setDuration(300).start();
        msg_box.clearAnimation();
        KeyBoard.getKeyBoard().OpenKeyBoard(null);
        Log.i("edgar", "Signal Open KeyBoard");
        isChat = true;
    }

    public final void ChatClose() {
        msg_messages.setVerticalScrollBarEnabled(false);
        msg_messages.setEnableScrolling(false);
        if(statusChat == 3) {
            msg_messages.animate().alpha(0.0f).setDuration(300).start();
        }
        msg_box.animate().alpha(0.0f).setDuration(300).start();
        msg_box.clearAnimation();
        KeyBoard.getKeyBoard().q();
        Log.i("edgar", "Signal Close KeyBoard");
        isChat = false;
    }

    public void setChatStatys(int i){
        if(i == 1){
            msg_messages.animate().alpha(1.0f).setDuration(300).start();
            statusChat = i;
        } else if(i == 3){
            msg_messages.animate().alpha(0.0f).setDuration(300).start();
            statusChat = i;
        }
    }

    public static ChatManager getChatManager(){
        return instance;
    }

    public void AddChatMessage(String msg){
        chatAdapter.addItem(msg);
    }

    public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
        Context context;
        ArrayList<String> chat_message;


        public ChatAdapter(Context context, ArrayList<String> chat_message){
            this.context = context;
            this.chat_message = chat_message;
        }

        @NonNull
        @Override
        public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.chat_message, parent, false);
            return new ChatViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
            String pon = String.valueOf(chat_message.get(position));
            int index = pon.indexOf("{");
            if(index == - 1) {
                holder.msg_driver.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {
                String firstEightChars = pon.substring(index + 1, index + 7);
                System.out.println(firstEightChars);
                holder.msg_driver.setBackgroundColor(Color.parseColor("#" + firstEightChars));
                index = -1;
            }
            System.out.println(pon);
            if(pon == "") {
                holder.msg_driver.setVisibility(View.GONE);
            }
            holder.msg.setText(Utils.transfromColors(pon));
        }

        @Override
        public int getItemCount() {
            return chat_message.size();
        }

        public class ChatViewHolder extends RecyclerView.ViewHolder {

            public TextView msg;
            public View msg_driver;

            public ChatViewHolder(View itemView) {
                super(itemView);
                msg = itemView.findViewById(R.id.msg_text);
                msg_driver = itemView.findViewById(R.id.msg_divider);
                msg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isChat == false) {
                            ChatOpen();
                            isChat = true;
                        } else {
                            ChatClose();
                            isChat = false;
                        }
                    }
                });
            }
        }

        public void addItem(String item) {
            aactivity.runOnUiThread(() -> {
                if(this.chat_message.size() > 40){
                    this.chat_message.remove(0);
                    notifyItemRemoved(0);
                }
                this.chat_message.add(" "+item+" ");
                notifyItemInserted(this.chat_message.size() - 1);
                if(!isChat) {
                    if (msg_messages.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                        msg_messages.scrollToPosition(this.chat_message.size() - 1);
                    }
                }
            });

        }

        public void scrollItem() {
            if(!isChat) {
                if (msg_messages.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    msg_messages.scrollToPosition(this.chat_message.size() - 1);
                }
            }
        }

    }
}
