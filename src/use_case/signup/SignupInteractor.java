package use_case.signup;

import entity.User;
import entity.UserFactory;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;


    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary, UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (signupInputData.isReturning()) {
            userPresenter.prepareReturningView();
        }
        else {
            if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
                userPresenter.prepareFailView("User already exists.");
            } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
                userPresenter.prepareFailView("Passwords don't match.");
            } else {

                User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
                userDataAccessObject.save(user);

                SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
                userPresenter.prepareSuccessView(signupOutputData);
            }
        }
    }
}