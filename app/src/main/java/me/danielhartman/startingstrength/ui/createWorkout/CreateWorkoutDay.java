package me.danielhartman.startingstrength.ui.createWorkout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.danielhartman.startingstrength.R;
import me.danielhartman.startingstrength.dagger.DaggerHolder;
import me.danielhartman.startingstrength.ui.accountManagement.LoginPresenter;

public class CreateWorkoutDay extends Fragment{
    private static final String TAG = CreateWorkoutDay.class.getSimpleName();
    private View rootView;
    @BindView(R.id.addExercise)
    Button addExerciseButton;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    FrameLayout mExerciseFrame;

    @Inject
    public LoginPresenter loginPresenter;

    @Inject
    public CreateWorkoutPresenter mPresenter;

    private CreateDayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_workout_day, container, false);
        ButterKnife.bind(this, rootView);
        DaggerHolder.getInstance().component().inject(this);
        mExerciseFrame = ((CreateWorkoutActivity)getActivity()).getExerciseFrame();
        mExerciseFrame.setVisibility(View.GONE);
        mPresenter.setAddFrameDisplayed(false);
        populateRecycler();
        return rootView;
    }

    public void populateRecycler(){
        mPresenter.setAdapter(new CreateDayAdapter(mPresenter.getSetsForGivenDay(mPresenter.getCurrentDay())));
        mRecyclerView.setAdapter(mPresenter.getAdapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplication()));
    }

    @OnClick(R.id.addExercise)
    public void addExerciseOnClick() {
        if (mPresenter.getAddFrameDisplayed()){
           setCreateExerciseInvisible();
        }else {
            setCreateExerciseVisible();
        }
    }

    @OnClick(R.id.finishDaysButton)
    public void finishDaysButtonClick(){
        String userId = loginPresenter.getUser().getUid();
        mPresenter.commitWorkoutToFirebase( userId);
    }

    @OnClick(R.id.createDayButton)
    public void createDayOnClick(){
        mPresenter.goToNextDay();
        mPresenter.viewExercisesForToday();
    }
    public void setCreateExerciseVisible(){
        mExerciseFrame.setVisibility(View.VISIBLE);
        addExerciseButton.setText("Hide");
        mPresenter.setAddFrameDisplayed(true);
    }
    public void setCreateExerciseInvisible(){
        mExerciseFrame.setVisibility(View.GONE);
        addExerciseButton.setText("Add Exercise");
        mPresenter.setAddFrameDisplayed(false);
    }

    @OnClick(R.id.previousDayButton)
    public void onPreviousDayClicked(){
        mPresenter.goToPreviousDay();
        mPresenter.viewExercisesForToday();
    }







}


