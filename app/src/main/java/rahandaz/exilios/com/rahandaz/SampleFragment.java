package rahandaz.exilios.com.rahandaz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by NaviD on 9/14/2017.
 */

public class SampleFragment extends Fragment {

    RecyclerView recyclerView;
    ProjectAdapter projectAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.page_recycler);
        ArrayList<ProjectModel> projectModels = new ArrayList<>();
        ProjectModel a = new ProjectModel("hello","salamAleyk","Man ye hello hastam",null,null,"2","hajHassan",5000,4000,354351);
        ProjectModel b = new ProjectModel("bye","salamAleyk","Man ye bye hastam",null,null,"5","hajHassan",30000,8000,413215);
        projectModels.add(a);
        projectModels.add(b);
        projectAdapter = new ProjectAdapter(getContext(), projectModels);
        recyclerView.setAdapter(projectAdapter);
//
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext()); // (Context context, int spanCount)
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }
}
