package struct;

public interface IAction {
    int getNInputNeurons();
    int getNOutputNeurons();

    void doFeedForward();
}
