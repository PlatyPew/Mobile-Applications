package mapp.noted;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class RecentArrayAdapter extends RecyclerView.Adapter<RecentArrayAdapter.ViewHolder> {


    private RecyclerViewClickListener mListener;

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private int listItemLayout;
    private String name;
    private ArrayList<Log> customerArrayList;
    // Constructor of the class
    public RecentArrayAdapter(int layoutId, ArrayList<Log> itemList, RecyclerViewClickListener listener,String name) {
        listItemLayout = layoutId;
        this.customerArrayList = itemList;
        this.mListener = listener;
        this.name=name;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return customerArrayList == null ? 0 : customerArrayList.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view,this.mListener);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        //This is where we place code so that the Android system can run them
        //to display the customer data inside each recyclerview rows.
        TextView textView_text = holder.textView_text;
        //TextView textView_mobileContact = holder.textView_mobileContact;
        TextView textView_date = holder.textView_date;
        String notename = customerArrayList.get(listPosition).getNote();
        String action = customerArrayList.get(listPosition).getAction();
        String from = customerArrayList.get(listPosition).getFrom();

        if (from.equals(name)){
            from="You";
        }
    String total =from+" "+action+" your note "+notename+".";
        textView_date.setText(customerArrayList.get(listPosition).getTime());
        textView_text.setText(total);

        //textView_mobileContact.setText(customerArrayList.get(listPosition).getNote());
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView_text;
        //public TextView textView_mobileContact;

        public TextView textView_date;

        private RecyclerViewClickListener mListener;
        public ViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;

            //Get the three variables, textView_name, textView_mobileContact and
            //button_edit to reference the elements which are defined the the XML in
            //the note_list_item.xml          textView_text= (TextView) itemView.findViewById(R.id.TextView_text);
            textView_date = (TextView) itemView.findViewById(R.id.TextView_date);
            textView_text = (TextView) itemView.findViewById(R.id.TextView_text);

        }
        @Override
        public void onClick(View view) {
            //Log.d("onclick", "onClick " + getLayoutPosition() + " " + textView_name.getText());
            mListener.onClick(view, getAdapterPosition());
        }
    }
}
