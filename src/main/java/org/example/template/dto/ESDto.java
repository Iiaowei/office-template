package org.example.template.dto;

import java.util.List;
import java.util.Map;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/4 11:14:49
 */
public class ESDto {
    private String _id;
    private String _index;
    private String _score;
    private Map<String, Object> _source;
    private String _type;
    private List<Integer> sort;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public String get_score() {
        return _score;
    }

    public void set_score(String _score) {
        this._score = _score;
    }

    public Map<String, Object> get_source() {
        return _source;
    }

    public void set_source(Map<String, Object> _source) {
        this._source = _source;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public List<Integer> getSort() {
        return sort;
    }

    public void setSort(List<Integer> sort) {
        this.sort = sort;
    }
}
