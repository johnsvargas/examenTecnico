package com.juancarlos.examentecnico.model.entities

data class GeneralResponse (val data:Map<String,Any>,val code:Int,val success:Boolean = false)