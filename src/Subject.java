public interface Subject
{
    void addObserver(User user);
    void removeObserver(User user);
    void notifyAllObservers(Notification notification);
}
