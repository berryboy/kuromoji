/**
 * This software is the property of Atilika Inc.  It can only be used
 * as described in the Software License Agreement and in no other way.
 *
 * Copyright © 2012 Atilika Inc.  All rights reserved.
 */

package com.atilika.kuromoji.dict;

import com.atilika.kuromoji.trie.DoubleArrayTrie;

public class DynamicDictionaries {

    private TokenInfoDictionary tokenInfoDictionary;
    private UnknownDictionary unknownDictionary;
    private ConnectionCosts connectionCosts;
    private DoubleArrayTrie doubleArrayTrie;
    private String directory;

    public DynamicDictionaries(String directory) {
        this.directory = directory;
        load();
    }

    private synchronized void load() {
        try {
            tokenInfoDictionary = TokenInfoDictionary.newInstance(directory);
            unknownDictionary = UnknownDictionary.newInstance(directory);
            connectionCosts = ConnectionCosts.newInstance(directory);
            doubleArrayTrie = DoubleArrayTrie.newInstance(directory);
        } catch (Exception ex) {
            throw new RuntimeException("could not load dictionaries");
        }
    }

    public DoubleArrayTrie getTrie() {
        return doubleArrayTrie;
    }

    public TokenInfoDictionary getDictionary() {
        return tokenInfoDictionary;
    }

    public UnknownDictionary getUnknownDictionary() {
        return unknownDictionary;
    }

    public ConnectionCosts getCosts() {
        return connectionCosts;
    }
}
