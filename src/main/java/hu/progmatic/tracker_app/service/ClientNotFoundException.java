package hu.progmatic.tracker_app.service;

public class ClientNotFoundException extends Throwable {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
