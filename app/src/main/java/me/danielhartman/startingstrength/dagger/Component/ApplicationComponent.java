package me.danielhartman.startingstrength.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import me.danielhartman.startingstrength.ui.accountManagement.AccountActivity;
import me.danielhartman.startingstrength.ui.accountManagement.CreateAccountFragment;
import me.danielhartman.startingstrength.ui.accountManagement.Login_Fragment;
import me.danielhartman.startingstrength.ui.createWorkout.CreateWorkoutName;
import me.danielhartman.startingstrength.ui.MainMenu_Fragment;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewPlan_Fragment;
import me.danielhartman.startingstrength.ui.viewWorkout.ViewWorkout_Fragment;
import me.danielhartman.startingstrength.dagger.module.ApplicationModule;

@Singleton
@Component(modules = { ApplicationModule.class})
public interface ApplicationComponent {

    void inject(ViewWorkout_Fragment fragment);
    void inject(ViewPlan_Fragment fragment);
    void inject(Login_Fragment fragment);
    void inject(CreateAccountFragment fragment);
    void inject(AccountActivity activity);
    void inject(CreateWorkoutName fragment);
    void inject(MainMenu_Fragment fragment);

}
