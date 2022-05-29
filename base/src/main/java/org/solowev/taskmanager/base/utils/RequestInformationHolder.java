package org.solowev.taskmanager.base.utils;

public final class RequestInformationHolder {
    private static final ThreadLocal<RequestInformation> requestInformation = new ThreadLocal<>();

    public static RequestInformation getRequestInformation() {
        RequestInformation request = requestInformation.get();
        if (request == null) {
            request = new RequestInformation();
            requestInformation.set(request);
        }
        return request;
    }
}
