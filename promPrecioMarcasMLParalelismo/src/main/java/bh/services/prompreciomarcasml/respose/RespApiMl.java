package bh.services.prompreciomarcasml.respose;

import java.util.List;
import java.util.concurrent.Callable;

public class RespApiMl  implements Callable<String>{

	private List<Result> results;
	private Paging paging;

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public String call() throws InterruptedException,Exception {
		return paging.getOffset().toString();
	}
}