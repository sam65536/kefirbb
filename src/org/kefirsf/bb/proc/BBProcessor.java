package org.kefirsf.bb.proc;

import org.kefirsf.bb.TextProcessorAdapter;
import org.kefirsf.bb.TextProcessorNestingException;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * The bbcode processor. You can use the standard code set or define other.
 *
 * @author Kefir
 */
public final class BBProcessor extends TextProcessorAdapter {
    public static final String LOGGER_CONTEXT = "kefirbb.context";
    public static final String LOGGER_PARSE = "kefirbb.parse";
    public static final String LOGGER_GENERATE = "kefirbb.generate";

    /**
     * BB-codes
     */
    private ProcScope scope = null;
    private ProcTemplate prefix = null;
    private ProcTemplate suffix = null;
    private Map<String, CharSequence> params = null;
    private Set<PatternConstant> constants;

    private int nestingLimit = 0;
    private boolean propagateNestingException = false;

    /**
     * Create the bbcode processor
     */
    public BBProcessor() {
    }

    /**
     * Process bbcodes
     * 1. Escape the xml special symbols
     * 2. replace bbcodes to HTML-tags
     * 3. replace symbols \r\n to HTML-tag "&lt;br/&gt;"
     *
     * @param source the source string
     * @return result string
     * @see org.kefirsf.bb.TextProcessor#process(CharSequence)
     */
    public CharSequence process(CharSequence source) {
        Context context = new Context();
        StringBuilder target = new StringBuilder(source.length());
        context.setTarget(target);
        Source source1 = new Source(source);
        source1.setConstantSet(constants);
        context.setSource(source1);
        context.setScope(scope);
        context.setNestingLimit(nestingLimit*2);
        if (params != null) {
            for (Map.Entry<String, CharSequence> entry : params.entrySet()) {
                context.setAttribute(entry.getKey(), entry.getValue());
            }
        }

        try {
            prefix.generate(context);
            boolean success = scope.process(context);
            suffix.generate(context);

            if(!success){
                target = new StringBuilder(0);
            }
        } catch (NestingException e) {
            target = new StringBuilder(0);
            if(propagateNestingException){
                throw new TextProcessorNestingException(nestingLimit);
            }
        }

        return target;
    }

    /**
     * Set the root scope of text processor.
     *
     * @param scope root code scope
     * @throws IllegalStateException if scope already setted
     */
    public void setScope(ProcScope scope) throws IllegalStateException {
        if (this.scope == null) {
            this.scope = scope;
        } else {
            throw new IllegalStateException("Can't change the root scope.");
        }
    }

    /**
     * Set the prefix for text processor
     *
     * @param prefix template wich uses to create prefix
     * @throws IllegalStateException If prefix already setted
     */
    public void setPrefix(ProcTemplate prefix) throws IllegalStateException {
        if (this.prefix == null) {
            this.prefix = prefix;
        } else {
            throw new IllegalStateException("Can't change the prefix.");
        }
    }

    /**
     * Set the suffix for text processor
     *
     * @param suffix template wich uses to create prefix
     * @throws IllegalStateException If suffix already setted
     */
    public void setSuffix(ProcTemplate suffix) {
        if (this.suffix == null) {
            this.suffix = suffix;
        } else {
            throw new IllegalStateException("Can't change the suffix.");
        }
    }

    /**
     * Set text processor parameters map.
     *
     * @param params parameters
     */
    public void setParams(Map<String, CharSequence> params) {
        if (this.params == null) {
            this.params = Collections.unmodifiableMap(params);
        } else {
            throw new IllegalStateException("Can't change parameters.");
        }
    }

    public void setConstants(Set<PatternConstant> constants) {
        this.constants = constants;
    }

    public void setNestingLimit(int nestingLimit) {
        this.nestingLimit = nestingLimit;
    }

    public void setPropagateNestingException(boolean propagateNestingException) {
        this.propagateNestingException = propagateNestingException;
    }
}
