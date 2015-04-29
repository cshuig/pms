package com.jiezhu.pms.web.comm;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import com.jiezhu.pms.comm.except.SysException;
import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.comm.page.CustomPaginatedList;
import com.jiezhu.pms.comm.page.Pageable;
import com.jiezhu.pms.comm.page.PaginatedList;
import com.jiezhu.pms.comm.page.impl.PageRequest;
import com.jiezhu.pms.comm.util.Constants;
import com.jiezhu.pms.comm.util.ShiroUtil;

/**
 * @Description 控制器基类(SBS权限管理部分迁移使用)
 * @author
 * @date 2012-11-15
 * @version 1.0
 */
public abstract class _BaseController extends MultiActionController {

    /**
     * @Description 创建ModelAndView实例
     * @return
     * @author
     */
    protected ModelAndView createMAV() {
        return new ModelAndView();
    }

    /**
     * @Description 创建带有试图名称的ModelAndView实例
     * @param viewName
     * @return
     * @author
     */
    protected ModelAndView createMAV(String viewName) {
        return new ModelAndView(viewName);
    }

    /**
     *
     * @param viewName
     * @param page
     * @return
     */
    protected <T> ModelAndView createMAV(String viewName, Page<T> page) {
        return createMAV(viewName).addObject("pageList", page);
    }

    @Override
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);

        // 扩展日期绑定
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                try {
                    setValue(DateUtils.parseDate(value, candidateDatePatterns));
                } catch (ParseException e) {
                    log.error(e.getMessage());
                    setValue(null);
                }
            }

            @Override
            public String getAsText() {
                return String.valueOf(getValue());
            }

        });
    }

    /**
     * @Description 通过请求参数绑定Model
     * @param request
     * @param clazz
     *            绑定类
     * @return
     * @throws Exception
     * @author
     */
    protected <T> T bindModel(HttpServletRequest request, Class<T> clazz)
            throws Exception {
        T object = (T) newCommandObject(clazz);
        bind(request, object);
        return object;
    }

    /**
     * @Description 获取分页信息
     * @param request
     * @return
     * @author
     */
    protected Pageable findPage(HttpServletRequest request) {
        return findPage(request, PAGE_PARAM_START, PAGE_PARAM_LIMIT);
    }

    protected Pageable findPageByPageSize(HttpServletRequest request) {
        Pageable pageable = null;
        if (request.getParameter("pageSize") == null
                || request.getParameter("pageSize").equals("")) {
            Integer start = findIntegerParameterValue(request, Constants._page);
            pageable = new PageRequest(start == null ? 1 : start,
                    Constants.DEFAULT_LIMIT);
        } else {
            pageable = this.findPage(request);
        }

        return pageable;
    }

    protected <T> Page<T> findPageFinal(HttpServletRequest request) {
        Pageable pageable = findPageByPageSize(request);

        return findPageFinal(pageable);
    }

    protected <T> Page<T> findPageFinal(Pageable requestPage) {
        Page<T> page = new Page<T>();

        page.setPageLimit(requestPage.getPageLimit());
        page.setPageStart(requestPage.getPageStart());

        return page;
    }

    /**
     * @Description 获取分页信息
     * @param request
     * @param pageFieldName
     *            起始页字段名称
     * @param pageSizeFieldName
     *            单页总量字段名称
     * @return
     * @author
     */
    protected Pageable findPage(HttpServletRequest request,
            String pageFieldName, String pageSizeFieldName) {
        Validate.notBlank(pageFieldName, "page field name required");
        Validate.notBlank(pageSizeFieldName, "pageSize field name required");
        Integer start = findIntegerParameterValue(request, pageFieldName);
        Integer limit = findIntegerParameterValue(request, pageSizeFieldName);
        if (limit == null) {
            throw new SysException("pageSize is required");
        }
        // 限制pageSize <= 100
        if (limit > 100) {
            log.warn("pageSize must be less than 100");
            limit = 100;
        }
        ;
        return new PageRequest(start == null ? 1 : start, limit);
    }

    /**
     * @Description 执行分页处理
     * @param pageable
     *            分页结果
     * @param start
     * @param limit
     * @return
     * @author
     */
    protected PaginatedList doPaging(Pageable pageable, int start, int limit) {
        // 分页获取记录
        int pageNumber = start;

        CustomPaginatedList pageList = new CustomPaginatedList();

        // 设置当前页数
        pageList.setPageNumber(pageNumber);
        // 设置当前页列表
        pageList.setList(pageable.getResult(List.class));
        // 设置page size
        pageList.setObjectsPerPage(limit);
        // 设置总数
        pageList.setFullListSize(pageable.getCount());

        return pageList;
    }

    /**
     * @Description 执行分页处理
     * @param request
     * @param pageable
     *            分页结果
     * @return
     * @author
     */
    protected PaginatedList doPaging(HttpServletRequest request,
            Pageable pageable) {
        // 分页获取记录
        int pageNumber;
        if (StringUtils.isNotBlank(request.getParameter(PAGE_PARAM_START))) {
            pageNumber = findIntegerParameterValue(request, PAGE_PARAM_START);
        } else {
            pageNumber = 1;
        }

        CustomPaginatedList pageList = new CustomPaginatedList();

        // 设置当前页数
        pageList.setPageNumber(pageNumber);
        // 设置当前页列表
        pageList.setList(pageable.getResult(List.class));
        // 设置page size
        pageList.setObjectsPerPage(this.findIntegerParameterValue(request,
                PAGE_PARAM_LIMIT));
        // 设置总页数
        pageList.setFullListSize(pageable.getCount());

        return pageList;
    }

    /**
     * @Description 从请求中获取Integer类型参数
     * @param request
     * @param name
     *            参数名称
     * @return
     * @author
     */
    protected Integer findIntegerParameterValue(HttpServletRequest request,
            String name) {
        String pv = WebUtils.findParameterValue(request, name);
        return StringUtils.isBlank(pv) ? null : Integer.parseInt(pv);
    }

    /**
     * @Description 从请求中获取Long类型参数
     * @param request
     * @param name
     *            参数名称
     * @return
     * @author
     */
    protected Long findLongParameterValue(HttpServletRequest request,
            String name) {
        String pv = WebUtils.findParameterValue(request, name);
        return StringUtils.isBlank(pv) ? null : Long.parseLong(pv);
    }

    /**
     * @Description 从请求中获取BigDecimal类型参数
     * @param request
     * @param name
     *            参数名称
     * @return
     * @author
     */
    protected BigDecimal findBigDecimalParameterValue(
            HttpServletRequest request, String name) {
        String pv = WebUtils.findParameterValue(request, name);
        return StringUtils.isBlank(pv) ? null : new BigDecimal(pv);
    }

    /**
     * @Description 从请求中获取String类型参数
     * @param request
     * @param name
     *            参数名称
     * @return
     * @author
     * @throws UnsupportedEncodingException
     */
    protected String findStringParameterValue(HttpServletRequest request,
            String name) throws UnsupportedEncodingException {
        String result = WebUtils.findParameterValue(request, name);
        if (result != null) {
            return URLDecoder.decode(result, "utf-8");
        }
        return null;
    }

    /**
     * @Description 从请求中获取Boolean类型参数
     * @param request
     * @param name
     *            参数名称
     * @return
     * @author
     */
    protected Boolean findBooleanParameterValue(HttpServletRequest request,
            String name) {
        String pv = WebUtils.findParameterValue(request, name);
        return StringUtils.isBlank(pv) ? null : Boolean.parseBoolean(pv);
    }

    /**
     * @Description 从请求中获取Date类型参数
     * @param request
     * @param name
     *            参数名称
     * @param datePattern
     *            日期模式
     * @return
     * @throws ParseException
     * @author
     */
    protected Date findDateParameterValue(HttpServletRequest request,
            String name, String datePattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(datePattern);
        String pv = WebUtils.findParameterValue(request, name);
        return StringUtils.isBlank(pv) ? null : dateFormat.parse(WebUtils
                .findParameterValue(request, name));
    }

    protected Locale getLocal(HttpServletRequest request) {
        return RequestContextUtils.getLocale(request);
    }

    protected void returnJson(HttpServletResponse response, String json)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(json);
    }

    /**
     * 分页请求起始参数
     */
    protected final String PAGE_PARAM_START = "page";

    /**
     * 每页显示数量
     */
    protected final String PAGE_PARAM_LIMIT = "pageSize";

    /**
     * 候选日期模式
     */
    private String[] candidateDatePatterns;

    public void setCandidateDatePatterns(String[] candidateDatePatterns) {
        this.candidateDatePatterns = candidateDatePatterns;
    }

    public Integer getCompanyId() {
        return ShiroUtil.getShiroUser().getCompanyId();
    }

    public String getCompanyName() {
        return ShiroUtil.getShiroUser().getCompany();
    }

    public Integer getUserId() {
        return ShiroUtil.getShiroUser().getUserId();
    }

    protected transient Logger log = LoggerFactory.getLogger(this.getClass());

}
