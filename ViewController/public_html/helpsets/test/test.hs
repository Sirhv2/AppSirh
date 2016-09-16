<?xml version="1.0" encoding="ISO-8859-1" ?>

<helpset>

<title>Sistema de Ayuda en L&#237;nea SIRH</title>

<maps>
   <mapref location="map.xml"/>
</maps>

<!-- Note: OHW does not support the <wintype> tag -->

<links>
   <linkref location="linklist.xml"/> 
</links>
<view>
   <label>Contenido</label>
   <title>Contenido</title>
   <type>oracle.help.navigator.tocNavigator.TOCNavigator</type>
   <data engine="oracle.help.engine.XMLTOCEngine">toc.xml</data>
</view>
<!--
<view>
   <label>HHC-TOC Navigator</label>
   <title>HHC-based TOC</title>
   <type>oracle.help.navigator.tocNavigator.TOCNavigator</type>
   <data engine="oracle.help.engine.HHCEngine">toc.hhc</data>
</view>
<view>
   <label>Default TOC Navigator</label>
   <title>Default based TOC</title>
   <type>oracle.help.navigator.tocNavigator.TOCNavigator</type>
   <data>toc.xml</data>
</view>-->
</helpset>

