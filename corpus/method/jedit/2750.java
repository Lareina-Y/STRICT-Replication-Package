//}}}
//}}}
//{{{ Getters/setter methods for various buffer meta-data
//{{{ getLastModified() method
/**
	 * @return the last time jEdit modified the file on disk.
	 * This method is thread-safe.
	 */
public long getLastModified() {
    return modTime;
}