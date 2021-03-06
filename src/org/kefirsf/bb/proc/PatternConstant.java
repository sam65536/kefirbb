package org.kefirsf.bb.proc;

/**
 * Constant element of pattern or template
 *
 * @author Kefir
 */
public class PatternConstant implements ProcPatternElement {
    /**
     * Constant value
     */
    private final String value;
    private final char[] chars;

    /**
     * Mark ignore case
     */
    private final boolean ignoreCase;

    /**
     * Length of constant value
     */
    private final int valueLength;

    /**
     * Don't move the cursor offset.
     */
    private final boolean ghost;

    /**
     * Create constant element.
     *
     * @param value constant value
     * @param ignoreCase ignore text case when parse a constant
     */
    public PatternConstant(String value, boolean ignoreCase) {
        this.value = value;
        this.chars = value.toCharArray();
        this.valueLength = value.length();
        this.ignoreCase = ignoreCase;
        this.ghost = false;
    }

    /**
     * Create constant element.
     *
     * @param value constant value
     * @param ignoreCase ignore text case when parse a constant
     * @param ghost don't move the cursor after parsing
     */
    public PatternConstant(String value, boolean ignoreCase, boolean ghost) {
        this.value = value;
        this.chars = value.toCharArray();
        this.valueLength = value.length();
        this.ignoreCase = ignoreCase;
        this.ghost = ghost;
    }

    /**
     * Parse constant
     *
     * @param context    current context
     * @param terminator not used
     * @return true - if next sequence in source equals to this constant value,
     *         false - other
     */
    public boolean parse(Context context, ProcPatternElement terminator) {
        if (isNextIn(context)) {
            if(!ghost) {
                context.getSource().incOffset(valueLength);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check equals next sequence in source to this constant
     *
     * @param context current context
     * @return true if next subsequence is equals
     *         false other
     */
    public boolean isNextIn(Context context) {
        return context.getSource().nextIs(this);
    }

    /**
     * Find this constant.
     *
     * @param source text source
     * @return смещение константы
     */
    public int findIn(Source source) {
        return source.find(this);
    }

    public String getValue() {
        return value;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public char[] getCharArray(){
        return chars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatternConstant that = (PatternConstant) o;

        if (ignoreCase != that.ignoreCase) return false;
        //noinspection RedundantIfStatement
        if (!value.equals(that.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + (ignoreCase ? 1 : 0);
        return result;
    }

    /**
     * @return string representation of this object.
     */
    @Override
    public String toString() {
        return value;
    }
}
