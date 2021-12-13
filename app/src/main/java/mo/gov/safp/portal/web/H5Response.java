package mo.gov.safp.portal.web;

public class H5Response<T> {
    public boolean success;
    public String message;
    public T data;
    public int code;
    public String tag;

    public static final int CODE_DEFAULT = 0;

    public H5Response() {
    }

    public H5Response(boolean success) {
        this.success = success;
    }

    public H5Response(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public H5Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public H5Response(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public H5Response(T data) {
        this(true, data);
    }

    public H5Response(boolean success, String message, T data, int code) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public H5Response(boolean success, String message, T data, int code, String tag) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code = code;
        this.tag = tag;
    }
}
