package tutoring.javastudy.base.service;

public class Supplier<T> implements java.util.function.Supplier<T> {
    @Override
    public T get() {
        try {
            // noinspection unchecked
            Class<T> mClass = (Class<T>) ClassUtils.getReclusiveGenericClass(getClass(), 0);
            if (mClass != null) {
                return mClass.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
