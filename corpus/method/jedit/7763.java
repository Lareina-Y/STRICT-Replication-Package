@Override
public int read(char[] cbuf) throws IOException {
    return read(cbuf, 0, cbuf.length);
}