package com.okta.examples.apiamhooks.model.hooks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TokenPatchResponse {

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Value> getValue() {
		return value;
	}

	public void setValue(List<Value> value) {
		this.value = value;
	}

	private String type;
    private List<Value> value = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Value {

        public String getOp() {
			return op;
		}
		public void setOp(String op) {
			this.op = op;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		private String op;
        private String path;
        private Object value;
    }
}
