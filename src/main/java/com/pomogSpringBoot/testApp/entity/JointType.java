package com.pomogSpringBoot.testApp.entity;

public enum JointType {
    SPHERICAL_CUP,
    SPHERICAL_BALL,
    CONE_CONE,
    CONE_SOCKET,
    FLAT_FLANGE,
    SCREW_THREAD
}

/*
Conical
24/29 cone
24/29 socket

Spherical
S35 ball
S35 cup

Flat-flange
100

Screw-thread
28

^(?:\d{1,2}\/\d{1,2} (cone|socket)|\w\d{1,2} (ball|cup)|\d{1,3})$

 */
