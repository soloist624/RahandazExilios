package rahandaz.exilios.com.rahandaz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.numberprogressbar.NumberProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NaviD on 8/15/2017.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectItemHolder> {

    ArrayList<ProjectModel> mData;
    Context context;
    private LayoutInflater inflater;

    public ProjectAdapter(Context context, ArrayList<ProjectModel> data) {
        inflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @Override
    public ProjectItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.project_item, parent, false);
        ProjectItemHolder holder = new ProjectItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProjectItemHolder holder, int position) {
        ProjectModel current = mData.get(position);
        holder.setData(current, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void projectDetailsActivityStarter(int pos){
        Intent detailActivityLauncher = new Intent( context , ProjectDetailActivity.class);
//        detailActivityLauncher.putExtra(ProjectInfoActivity.PRODUCT_POSITION, pos);
//        detailActivityLauncher.putExtra(ProjectInfoActivity.TOTAL_PRODUCT,projectInfoActivity.totalProducts.get(pos) );
//        detailActivityLauncher.putExtra(InfoInputActivity.OFFLINE_PARTS, projectInfoActivity.getDbPieces());
        context.startActivity(detailActivityLauncher);
    }

    class ProjectItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView background;
        ImageView projectIMG, userIMG;
        TextView category, currentPercent, remainingTime, description, projectTitle, creator;
        NumberProgressBar progressBar;
        int position;
        ProjectModel current;

        public ProjectItemHolder(View itemView) {
            super(itemView);
            background = (CardView) itemView.findViewById(R.id.project_item_card);
            projectIMG = (ImageView) itemView.findViewById(R.id.project_image_view);
            userIMG = (ImageView) itemView.findViewById(R.id.user_profile_image_view);
            category = (TextView) itemView.findViewById(R.id.item_cat);
            currentPercent = (TextView) itemView.findViewById(R.id.current_percentage);
            remainingTime = (TextView) itemView.findViewById(R.id.time_left);
            projectTitle = (TextView) itemView.findViewById(R.id.product_name);
            creator = (TextView) itemView.findViewById(R.id.creator);
            description = (TextView) itemView.findViewById(R.id.item_desciption);
            progressBar = (NumberProgressBar) itemView.findViewById(R.id.number_progress_bar);

        }

        public void setData(ProjectModel current, int position) {

            int temp = 100*current.getCurrentAmount()/current.getGoalAmount();
            progressBar.setProgress(temp);
            category.setText(current.getCategory());
            currentPercent.setText((current.getCurrentAmount()/current.getGoalAmount()*100)+"");
            remainingTime.setText(current.getRemainingTime());
            projectTitle.setText(current.getTitle());
            creator.setText(current.getCreator());
            description.setText(current.getDescription());
            this.position = position;
//            Glide.with(context).load(current.getImageUrl()).into(projectIMG);
//            Glide.with(context).load(current.getUserImageUri()).into(userIMG);
            this.current = current;
            background.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            projectDetailsActivityStarter(position);
        }
    }
}
