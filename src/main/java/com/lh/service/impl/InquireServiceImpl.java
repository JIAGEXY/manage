package com.lh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lh.dto.MsOrderDTO;
import com.lh.entity.MsOrder;
import com.lh.entity.MsOrderExample;
import com.lh.mapper.MsOrderMapper;
import com.lh.service.InquireService;
import com.lh.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;

@Service
public class InquireServiceImpl implements InquireService{

    @Autowired
    private MsOrderMapper msOrderMapper;

    @Override
    public R find(int pageIndex, int pageSum, Integer status) {
        PageHelper.startPage(pageIndex,pageSum);
        List<MsOrderDTO> list = new ArrayList<>();
        MsOrderExample example = new MsOrderExample();
        if (!StringUtils.isEmpty(status)){
            MsOrderExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(status);
        }
        List<MsOrder> msOrders = msOrderMapper.selectByExample(example);
        for (MsOrder msOrder : msOrders) {
            MsOrderDTO msOrder1 = new MsOrderDTO();
            msOrder1.setOrderid(msOrder.getOrderid());
            msOrder1.setStartdate(msOrder.getStartdate());
            msOrder1.setOutdate(msOrder.getOutdate());
            msOrder1.setMoney(msOrder.getMoney());
            msOrder1.setDeposit(msOrder.getDeposit());
            msOrder1.setStatus(msOrder.getStatus());

            list.add(msOrder1);
        }

        PageInfo pageInfo = new PageInfo(list);

        return list.size()>0?R.ok().put("data",pageInfo):R.error("查询错误");
    }
}
