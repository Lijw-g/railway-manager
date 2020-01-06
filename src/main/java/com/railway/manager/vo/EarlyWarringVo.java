package com.railway.manager.vo;

import com.railway.manager.model.EarlyWarring;
import lombok.Data;

import java.util.List;

@Data
public class EarlyWarringVo {
    private int count;
    private List<EarlyWarring> earlyWarrings;
}
