package com.jiezhu.pms.web.channel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jiezhu.pms.comm.nmybatis.Page;
import com.jiezhu.pms.comm.util.Constants;
import com.jiezhu.pms.comm.util.JsonUtils;
import com.jiezhu.pms.entity.Channel;
import com.jiezhu.pms.entity.ChannelBill;
import com.jiezhu.pms.entity.ChannelOperatorRecord;
import com.jiezhu.pms.service.channel.ChannelBillService;
import com.jiezhu.pms.service.channel.ChannelOpeRecordService;
import com.jiezhu.pms.service.channel.ChannelService;
import com.jiezhu.pms.service.comm.ComboBoxInitService;
import com.jiezhu.pms.web.comm._BaseController;

/**
 * @ClassName: ChannelManage
 * @Description: 渠道公司信息管理
 * @author: 张波波
 * @date: 2014年12月25日 下午1:57:14
 */
@Controller
@RequestMapping("/channel")
public class ChannelManageController extends _BaseController {

    @Autowired
    private ComboBoxInitService comboBoxInitService;

    @Autowired
    private ChannelService channelService;
    
    @Autowired
    private ChannelBillService channelBillService;
    
    @Autowired
    private ChannelOpeRecordService channelOpeRecordService;

    /**
     * @Description: 渠道管理主界面:初始化
     * @return: ModelAndView 主界面
     * @Author: 张波波
     * @date: 2014年12月25日
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public ModelAndView channelManage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("channel/channelManage");
        // 检索渠道公司信息
        Page<Channel> page = new Page<Channel>();
        page.setPageLimit(Constants.DEFAULT_LIMIT);
        List<Channel> chanList = channelService.searchChannelInfoForPage(page);
        // 生成view
        mv.addObject("accountBoxList",
                comboBoxInitService.searchComboxList("channel_status_enum")); // 账户状态
        mv.addObject("compTypeList", 
                comboBoxInitService.searchComboxList("channel_type_enum")); // 公司性质
        mv.addObject("chanList", chanList); // 渠道商列表
        mv.addObject("page", page); // 分页情况

        return mv;
    }

    /**
     * @Description: 主界面:查询
     * @param: 搜索条件
     * @return: ModelAndView 主界面
     * @throws Exception JSON数据转换失败
     * @Author: 张波波S
     * @date: 2014年12月25日
     */
    @RequestMapping(value = "/list", method = { RequestMethod.POST })
    public void searchChannelInfo(Channel chan, Page<Channel> page,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("channel/channelManage");
        // 加载搜索条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("chan", chan);
        // 检索渠道公司信息
        page.setPageLimit(Constants.DEFAULT_LIMIT);
        page.setParams(params);
        page.setResults(channelService.searchChannelInfoForPage(page));
        // view生成
        mv.addObject("page", page); // 分页情况
        this.returnJson(response, JsonUtils.obj2JsonString(page));
    }

    /**
     * @Description: 新增渠道页面:初始化
     * @param: @return
     * @return: ModelAndView
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    @RequestMapping(value = "/add")
    public ModelAndView createChannelPageInit() {
        ModelAndView mv = new ModelAndView("channel/newChannel");
        mv.addObject("compTypeList", 
                comboBoxInitService.searchComboxList("channel_type_enum")); // 公司性质
        return mv;
    }

    /**
     * 
     * @Description: 新增渠道
     * @param: @param chan
     * @param: @return
     * @return: ModelAndView
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    @RequestMapping(value = "/add", params = { "companyName" })
    public ModelAndView createChannel(Channel chan) throws Exception {
        long insertResult = channelService.insertChannel(chan);
        if (insertResult == 0) {
            // TODO
            // 新增渠道信息失败
            // 重复插入的提示信息
        } else {
            // 新增渠道信息成功
            // 创建操作日志
            Map<String, String> detail = new HashMap<String, String>();
            detail.put("operator", "addChan");
            detail.put("chanId", String.valueOf(insertResult));
            detail.put("chanName", chan.getCompanyName());
            channelOpeRecordService.insertRecord(detail);
        }

        return createChannelPageInit();
    }

    /**
     * @Description: 上传合同扫描版
     * @param: 
     * @return: void
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    public void uploadContractScan() {
        // 文件大小限制
        // TODO
    }
    
    /**
     * @Description: 渠道商详情页面:初始化
     * @param: 渠道商ID
     * @return: ModelAndView 渠道商详情页面
     * @Author: 张波波
     * @date: 2014年12月26日
     */
    @RequestMapping(value="/detail")
    public ModelAndView channelDetailPageInit(@RequestParam String id) {
        ModelAndView mv = new ModelAndView("channel/detail");
        // TODO 页面权限设定
        // 渠道商信息
        Channel chan = new Channel();
        chan.setId(id);
        List<Channel> chanList = channelService.searchChannelInfo(chan);
        // 渠道商账单
        ChannelBill chanBil = new ChannelBill();
        chanBil.setChannelId(id); // 渠道商ID
        List<ChannelBill> chanBilList = channelBillService.searchChannelBill(chanBil);
        // 金额统计
        BigDecimal debitSum = new BigDecimal(0); // 借方金额统计
        BigDecimal creditSum = new BigDecimal(0); // 贷方金额统计
        for (ChannelBill bil : chanBilList) {
            debitSum.add(new BigDecimal(bil.getDebit()));
            creditSum.add(new BigDecimal(bil.getCredit()));
        }
        // 累计结余
        BigDecimal balance = creditSum.subtract(debitSum);
        // 操作记录
        ChannelOperatorRecord ope = new ChannelOperatorRecord();
        ope.setChannelId(id);
        List<ChannelOperatorRecord> opeList = 
                channelOpeRecordService.serachOperationList(ope);
        
        mv.addObject("compTypeList", 
                comboBoxInitService.searchComboxList("channel_type_enum"));  // 公司性质下拉框
        mv.addObject("chan", chanList.get(0));
        mv.addObject("chanBilList", chanBilList);
        mv.addObject("opeList", opeList);
        mv.addObject("debitSum", debitSum);
        mv.addObject("creditSum", creditSum);
        mv.addObject("balance", balance);
        return mv;
    }
    
    /**
     * @Description: 渠道商详情页面:添加账单
     * @param: 账单信息
     * @param: 账单列表
     * @return: void
     * @Author: 张波波
     * @date: 2015年1月4日
     */
    @RequestMapping(value="/bill/add")
    public void addChannelBill(ChannelBill chanBil, 
            HttpServletResponse response) {
        // TODO 校验
        channelBillService.insertChannelBill(chanBil);;
        // 检索账单信息 如何展示添加数据后的结果
//        this.returnJson(response, JsonUtils.obj2JsonString(page));
    }
    
    /**
     * @Description: 渠道商详情页面:更新账单
     * @param: 账单信息
     * @param: 账单列表 
     * @return: ModelAndView
     * @Author: 张波波
     * @date: 2015年1月4日
     */
    @RequestMapping(value="/bill/update")
    public ModelAndView updateChannelBill(ChannelBill ChanBil, 
            HttpServletResponse response) {
        // TODO 校验
        // 更新账单
        channelBillService.updateChannelBill(ChanBil);
        // TODO 如何展示更新数据后的结果
        return null;
    }
    
    /**
     * TODO
     * @Description: 渠道商详情页面:打印账单
     * @param: 
     * @return: ModelAndView
     * @Author: 张波波
     * @date: 2015年1月4日
     */
    @RequestMapping(value="/bill/print")
    public ModelAndView printBill() {
        return null;
    }
    
    /**
     * TODO
     * @Description: 渠道商详情页面:保存
     * @param: 
     * @return: ModelAndView
     * @Author: 张波波
     * @date: 2015年1月4日
     */
    @RequestMapping(value="/bill/")
    public ModelAndView testBill() {
        return null;
    }
}
