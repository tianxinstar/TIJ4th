package tian.utils.pojo;

/**
 * 
 * <B>Description:</B> TODO <br>
 * <B>Create on:</B> 2016年11月22日 下午2:18:43 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */
public class Result {

	public static final String SUCCESS = "200";

	public static final String SUCCESS_MSG = "成功";

	public static final String FAILED = "500";

	protected String code;// 状态码

	protected String sn;// 接口号

	protected String msg;// 信息

	private Object data;// 数据

	private String logSid;// 日志sessionId

	public Result() {
	}

	public Result(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Result(String code, String serviceNumber, Object data) {
		this.code = code;
		this.sn = serviceNumber;
		this.data = data;
	}

	public Result(String code, String serviceNumber, String msg, Object data) {
		this.code = code;
		this.sn = serviceNumber;
		this.data = data;
		this.msg = msg;
	}

	public static Result success(String msg, Object data) {
		return new Result(SUCCESS, "", msg, data);
	}

	/*
	 * public static Result success(Object data) { return new
	 * Result(ResultCodeConstant.SUCCESS,
	 * ServiceNumberSupport.getServiceNumber(), ReturnConst.SUCCESS.val(),
	 * data); }
	 */

	public static Result success(String msg) {
		return success(msg, null);
	}

	public static Result success() {
		return new Result(SUCCESS, null, SUCCESS_MSG, null);
	}

	public static Result failed(String msg, Object data) {
		return new Result(FAILED, null, msg, data);
	}

	public static Result failed(String msg) {
		return failed(msg, null);
	}

	public static Result handler(String code, String msg) {
		return new Result(code, null, msg, null);
	}

	public static Result handler(String code, String msg, Object data) {
		return new Result(code, null, msg, data);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn
	 *            the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the logSid
	 */
	public String getLogSid() {
		// if (StringUtils.isBlank(logSid))
		// logSid = "[" + DateUtils.datetimeFormatNow() + "][SID:" +
		// MDC.get(SystemProcessTimerFilter.SID) + "]";
		return logSid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Result [code=" + code + ", sn=" + sn + ", msg=" + msg + ", data=" + data + ", logSid=" + logSid + "]";
	}

	/**
	 * @param logSid
	 *            the logSid to set
	 */
	public void setLogSid(String logSid) {
		this.logSid = logSid;
	}

}
