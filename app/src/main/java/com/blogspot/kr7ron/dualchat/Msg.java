package com.blogspot.kr7ron.dualchat;

import java.io.Serializable;

public class Msg implements Serializable{
    public String txt;
    Msg(String s)
    {
        txt=s;
    }
}
