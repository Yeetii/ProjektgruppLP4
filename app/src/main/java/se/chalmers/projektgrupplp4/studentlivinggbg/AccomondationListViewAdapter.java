package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//This class adapts an Accomondation-object so it works in a list of Accomondations
public class AccomondationListViewAdapter extends ArrayAdapter<Accomondation> implements View.OnClickListener{

    private ArrayList<Accomondation> dataSet;
    Context mContext;
    private int lastPosition = -1;

    public AccomondationListViewAdapter(ArrayList<Accomondation> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }


    //When tapping on the star
    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Accomondation dataModel=(Accomondation)object;




        switch (v.getId())
        {
            case R.id.favourite:


                //TODO Add to favourites code here
                Snackbar.make(v, "Added to favourites", Snackbar.LENGTH_LONG).setAction("No action", null).show();
                break;
        }


    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Accomondation dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        AccomondationListViewHolder viewHolder; // view lookup cache stored in tag


        if (convertView == null) {

            viewHolder = new AccomondationListViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);

            viewHolder.txtAddress = (TextView) convertView.findViewById(R.id.address);
            viewHolder.txtHouseType = (TextView) convertView.findViewById(R.id.type);
            viewHolder.txtArea = (TextView) convertView.findViewById(R.id.area);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.price);
            viewHolder.txtSearchers = (TextView) convertView.findViewById(R.id.searchers);
            viewHolder.favourite = (ImageView) convertView.findViewById(R.id.favourite);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AccomondationListViewHolder) convertView.getTag();
        }


        lastPosition = position;


        viewHolder.txtAddress.setText(dataModel.getAddress());
        viewHolder.txtHouseType.setText(dataModel.getHouseType());
        viewHolder.txtArea.setText(dataModel.getArea());
        viewHolder.txtPrice.setText(dataModel.getPrice());
        viewHolder.txtSearchers.setText(dataModel.getSearchers());
        viewHolder.image.setImageResource(dataModel.getImage());
        viewHolder.favourite.setOnClickListener(this);
        viewHolder.favourite.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }


}
