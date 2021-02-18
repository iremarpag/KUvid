package Domain.Storage;

public interface iResourceManager {
    public void save(Object o, String storageName) throws Exception;
    public Object load(Object o, String storageName) throws Exception;
}

