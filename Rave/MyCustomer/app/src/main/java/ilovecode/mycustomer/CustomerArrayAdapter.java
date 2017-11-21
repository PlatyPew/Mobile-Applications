package ilovecode.mycustomer;
/**
 * Created by Ah Tan on 1/11/17.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;


public class CustomerArrayAdapter extends RecyclerView.Adapter<CustomerArrayAdapter.ViewHolder> {


    private RecyclerViewClickListener mListener;

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private int listItemLayout;
    private ArrayList<Customer> customerArrayList;
    // Constructor of the class
    public CustomerArrayAdapter(int layoutId, ArrayList<Customer> itemList, RecyclerViewClickListener listener) {
        listItemLayout = layoutId;
        this.customerArrayList = itemList;
        this.mListener = listener;
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
        TextView textView_name = holder.textView_name;
        //TextView textView_mobileContact = holder.textView_mobileContact;
        TextView textView_desc = holder.textView_desc;
        TextView textView_date = holder.textView_date;
        TextView textView_creat = holder.textView_creat;
        textView_desc.setText(customerArrayList.get(listPosition).getDesc());
        textView_date.setText(customerArrayList.get(listPosition).getDate());
        textView_name.setText(customerArrayList.get(listPosition).getName());
        textView_creat.setText("Creator : "+customerArrayList.get(listPosition).getUser());
        //textView_mobileContact.setText(customerArrayList.get(listPosition).getNote());
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView_name;
        //public TextView textView_mobileContact;
        public TextView textView_desc;
        public TextView textView_date;
        public TextView textView_creat;
        public TextView textView_perm;
        public Button button_view;

        public Button button_edit;
        public Button button_delete;
        private RecyclerViewClickListener mListener;
        public ViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;

            //Get the three variables, textView_name, textView_mobileContact and
            //button_edit to reference the elements which are defined the the XML in
            //the customer_list_item.xml.
            textView_name = (TextView) itemView.findViewById(R.id.TextView_Name);
            textView_desc = (TextView) itemView.findViewById(R.id.TextView_desc);
            textView_date = (TextView) itemView.findViewById(R.id.TextView_date);
            textView_creat = (TextView) itemView.findViewById(R.id.TextView_creator);

            button_view = (Button) itemView.findViewById(R.id.Button_view);
            button_edit = (Button) itemView.findViewById(R.id.Button_Edit);
            button_delete = (Button) itemView.findViewById(R.id.Button_Delete);

            //Defined a click listener only for the button which has the edit icon
            button_view.setOnClickListener(this);
            button_edit.setOnClickListener(this);
            button_delete.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + textView_name.getText());
            mListener.onClick(view, getAdapterPosition());
        }



    }




}
