//}}}
//{{{ getAutoReload() method
/**
	 * @return the status of the AUTORELOAD flag
	 * If true, reload changed files automatically
	 */
public boolean getAutoReload() {
    return getFlag(AUTORELOAD);
}