package com.trueMagic.entity.xmlEntityDefinition;

import com.artemis.ArchetypeBuilder;
import com.artemis.Component;
import com.trueMagic.entity.ProcessedArchetypeBuilder;
import org.w3c.dom.Element;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public interface XmlTagProcessor {
    public void process(ProcessedArchetypeBuilder archetypeBuilder, Element element);
    public String getTagName();
}
