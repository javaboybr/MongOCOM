/*
 * Copyright 2014 Thiago da Silva Gonzaga <thiagosg@sjrp.unesp.br>..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mongocom.methods;

import com.mongodb.BasicDBObject;

/**
 *
 * @author Thiago da Silva Gonzaga <thiagosg@sjrp.unesp.br>.
 */
public final class MongoQuery {
    
    private BasicDBObject query;
    private BasicDBObject constraits;
    private int limit;
    private int skip;

    public MongoQuery() {
        query = new BasicDBObject();
    }

    public MongoQuery(String field, Object value) {
        this();
        add(field, value);
    }

    /**
     * Set a criteria to the query
     *
     * @param field field name
     * @param value
     * @return the same object instance
     */
    public MongoQuery add(String field, Object value) {
        if (value instanceof MongoQuery) {
            MongoQuery q = (MongoQuery) value;
            query.append(field, q.getQuery());
        } else {
            query.append(field, value);
        }
        return this;
    }

    /**
     * Limit the fields returned in a document result set
     *
     * @param returnId return the _id field in the result set if true
     * @param fields field names to be returned in the result set
     */
    public void returnOnly(boolean returnId, String... fields) {
        constraits = new BasicDBObject();
        for (String field : fields) {
            constraits.append(field, 1);
        }
        if (!returnId) {
            constraits.append("_id", 0);
        }
    }

    /**
     * Remove the specified fields from the result document set
     *
     * @param fields fields to be removed
     */
    public void removeFieldsFromResult(String... fields) {
        constraits = new BasicDBObject();
        for (String field : fields) {
            constraits.append(field, 0);
        }
    }

    /**
     * mark to remove _id field from the document set
     */
    public void removeIdFromResult() {
        constraits.append("_id", 0);
    }

    public BasicDBObject getQuery() {
        return query;
    }

    public BasicDBObject getConstraits() {
        return constraits;
    }

    public int getLimit() {
        return limit;
    }

    public void limit(int limit) {
        this.limit = limit;
    }

    public int getSkip() {
        return skip;
    }

    public void skip(int skip) {
        this.skip = skip;
    }

    public String getQueryJson() {
        return query.toString();
    }

    public String getConstraitsJson() {
        return constraits.toString();
    }

}
