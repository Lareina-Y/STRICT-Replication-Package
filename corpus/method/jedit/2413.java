public Object invokeImpl(Object proxy, Method method, Object[] args) throws EvalError {
    String methodName = method.getName();
    CallStack callstack = new CallStack(namespace);
    /*
                If equals() is not explicitly defined we must override the
                default implemented by the This object protocol for scripted
                object.  To support XThis equals() must test for equality with
                the generated proxy object, not the scripted bsh This object;
                otherwise callers from outside in Java will not see a the
                proxy object as equal to itself.
            */
    BshMethod equalsMethod = null;
    try {
        equalsMethod = namespace.getMethod("equals", new Class[] { Object.class });
    } catch (UtilEvalError e) {
    }
    if (methodName.equals("equals") && equalsMethod == null) {
        Object obj = args[0];
        return Boolean.valueOf(proxy == obj);
    }
    /*
                If toString() is not explicitly defined override the default
                to show the proxy interfaces.
            */
    BshMethod toStringMethod = null;
    try {
        toStringMethod = namespace.getMethod("toString", new Class[] {});
    } catch (UtilEvalError e) {
    }
    if (methodName.equals("toString") && toStringMethod == null) {
        Class[] ints = proxy.getClass().getInterfaces();
        // XThis.this refers to the enclosing class instance
        StringBuilder sb = new StringBuilder(XThis.this.toString() + "\nimplements:");
        for (int i = 0; i < ints.length; i++) sb.append(" " + ints[i].getName() + ((ints.length > 1) ? "," : ""));
        return sb.toString();
    }
    Class[] paramTypes = method.getParameterTypes();
    return Primitive.unwrap(invokeMethod(methodName, Primitive.wrap(args, paramTypes)));
}