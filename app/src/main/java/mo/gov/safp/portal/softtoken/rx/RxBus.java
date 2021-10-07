package mo.gov.safp.portal.softtoken.rx;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public final class RxBus {

    private final Subject<Object> mBus;

    static volatile RxBus defaultInstance;

    private RxBus() {
        mBus = PublishSubject.create();
    }

    public static RxBus getDefault() {
        RxBus instance = defaultInstance;
        if (instance == null) {
            synchronized (RxBus.class) {
                instance = RxBus.defaultInstance;
                if (instance == null) {
                    instance = RxBus.defaultInstance = new RxBus();
                }
            }
        }
        return instance;
    }

    public void post(Object o) {
        mBus.onNext(o);
    }

    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }
}