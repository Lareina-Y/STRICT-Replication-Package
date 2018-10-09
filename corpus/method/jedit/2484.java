//}}}
//{{{ setDirty() method
/**
	 * @param d Sets the 'dirty' (changed since last save) flag of this buffer.
	 */
public void setDirty(boolean d) {
    boolean editable = isEditable();
    if (d) {
        if (editable)
            dirty = true;
    } else {
        dirty = false;
        // save/insert/undo/redo/undo
        if (!isUndoInProgress()) {
            // this ensures that undo can clear the dirty flag properly
            // when all edits up to a save are undone
            undoMgr.resetClearDirty();
        }
    }
}