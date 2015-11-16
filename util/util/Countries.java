package util.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Countries {
	public static final String[][] DATA = {
			// Africa
		    {"ALGERIA","Algiers"}, {"ANGOLA","Luanda"},
		    {"BENIN","Porto-Novo"}, {"BOTSWANA","Gaberone"},
		    {"BURKINA FASO","Ouagadougou"},
		    {"BURUNDI","Bujumbura"},
		    {"CAMEROON","Yaounde"}, {"CAPE VERDE","Praia"},
		    {"CENTRAL AFRICAN REPUBLIC","Bangui"},
		    {"CHAD","N’djamena"},  {"COMOROS","Moroni"},
		    {"CONGO","Brazzaville"}, {"DJIBOUTI","Dijibouti"},
		    {"EGYPT","Cairo"}, {"EQUATORIAL GUINEA","Malabo"},
		    {"ERITREA","Asmara"}, {"ETHIOPIA","Addis Ababa"},
		    {"GABON","Libreville"}, {"THE GAMBIA","Banjul"},
		    {"GHANA","Accra"}, {"GUINEA","Conakry"},
		    {"BISSAU","Bissau"},
		    {"COTE D’IVOIR (IVORY COAST)","Yamoussoukro"},
		    {"KENYA","Nairobi"}, {"LESOTHO","Maseru"},
		    {"LIBERIA","Monrovia"}, {"LIBYA","Tripoli"},
		    {"MADAGASCAR","Antananarivo"}, {"MALAWI","Lilongwe"},
		    {"MALI","Bamako"}, {"MAURITANIA","Nouakchott"},
		    {"MAURITIUS","Port Louis"}, {"MOROCCO","Rabat"},
		    {"MOZAMBIQUE","Maputo"}, {"NAMIBIA","Windhoek"},
		    {"NIGER","Niamey"}, {"NIGERIA","Abuja"},
		    {"RWANDA","Kigali"},
		    {"SAO TOME E PRINCIPE","Sao Tome"},
		    {"SENEGAL","Dakar"}, {"SEYCHELLES","Victoria"},
		    {"SIERRA LEONE","Freetown"}, {"SOMALIA","Mogadishu"},
		    {"SOUTH AFRICA","Pretoria/Cape Town"},
		    {"SUDAN","Khartoum"},
		    {"SWAZILAND","Mbabane"}, {"TANZANIA","Dodoma"},
		    {"TOGO","Lome"}, {"TUNISIA","Tunis"},
		    {"UGANDA","Kampala"},
		    {"DEMOCRATIC REPUBLIC OF THE CONGO (ZAIRE)",
		     "Kinshasa"},
		    {"ZAMBIA","Lusaka"}, {"ZIMBABWE","Harare"},
		    // Asia
		    {"AFGHANISTAN","Kabul"}, {"BAHRAIN","Manama"},
		    {"BANGLADESH","Dhaka"}, {"BHUTAN","Thimphu"},
		    {"BRUNEI","Bandar Seri Begawan"},
		    {"CAMBODIA","Phnom Penh"},
		    {"CHINA","Beijing"}, {"CYPRUS","Nicosia"},
		    {"INDIA","New Delhi"}, {"INDONESIA","Jakarta"},
		    {"IRAN","Tehran"}, {"IRAQ","Baghdad"},
		    {"ISRAEL","Jerusalem"}, {"JAPAN","Tokyo"},
		    {"JORDAN","Amman"}, {"KUWAIT","Kuwait City"},
		    {"LAOS","Vientiane"}, {"LEBANON","Beirut"},
		    {"MALAYSIA","Kuala Lumpur"}, {"THE MALDIVES","Male"},
		    {"MONGOLIA","Ulan Bator"},
		    {"MYANMAR (BURMA)","Rangoon"},
		    {"NEPAL","Katmandu"}, {"NORTH KOREA","P’yongyang"},
		    {"OMAN","Muscat"}, {"PAKISTAN","Islamabad"},
		    {"PHILIPPINES","Manila"}, {"QATAR","Doha"},
		    {"SAUDI ARABIA","Riyadh"}, {"SINGAPORE","Singapore"},
		    {"SOUTH KOREA","Seoul"}, {"SRI LANKA","Colombo"},
		    {"SYRIA","Damascus"},
		    {"TAIWAN (REPUBLIC OF CHINA)","Taipei"},
		    {"THAILAND","Bangkok"}, {"TURKEY","Ankara"},
		    {"UNITED ARAB EMIRATES","Abu Dhabi"},
		    {"VIETNAM","Hanoi"}, {"YEMEN","Sana’a"},
		    // Australia and Oceania
		    {"AUSTRALIA","Canberra"}, {"FIJI","Suva"},
		    {"KIRIBATI","Bairiki"},
		    {"MARSHALL ISLANDS","Dalap-Uliga-Darrit"},
		    {"MICRONESIA","Palikir"}, {"NAURU","Yaren"},
		    {"NEW ZEALAND","Wellington"}, {"PALAU","Koror"},
		    {"PAPUA NEW GUINEA","Port Moresby"},
		    {"SOLOMON ISLANDS","Honaira"}, {"TONGA","Nuku’alofa"},
		    {"TUVALU","Fongafale"}, {"VANUATU","< Port-Vila"},
		    {"WESTERN SAMOA","Apia"},
		    // Eastern Europe and former USSR
		    {"ARMENIA","Yerevan"}, {"AZERBAIJAN","Baku"},
		    {"BELARUS (BYELORUSSIA)","Minsk"},
		    {"BULGARIA","Sofia"}, {"GEORGIA","Tbilisi"},
		    {"KAZAKSTAN","Almaty"}, {"KYRGYZSTAN","Alma-Ata"},
		    {"MOLDOVA","Chisinau"}, {"RUSSIA","Moscow"},
		    {"TAJIKISTAN","Dushanbe"}, {"TURKMENISTAN","Ashkabad"},
		    {"UKRAINE","Kyiv"}, {"UZBEKISTAN","Tashkent"},
		    // Europe
		    {"ALBANIA","Tirana"}, {"ANDORRA","Andorra la Vella"},
		    {"AUSTRIA","Vienna"}, {"BELGIUM","Brussels"},
		    {"BOSNIA","-"}, {"HERZEGOVINA","Sarajevo"},
		    {"CROATIA","Zagreb"}, {"CZECH REPUBLIC","Prague"},
		    {"DENMARK","Copenhagen"}, {"ESTONIA","Tallinn"},
		    {"FINLAND","Helsinki"}, {"FRANCE","Paris"},
		    {"GERMANY","Berlin"}, {"GREECE","Athens"},
		    {"HUNGARY","Budapest"}, {"ICELAND","Reykjavik"},
		    {"IRELAND","Dublin"}, {"ITALY","Rome"},
		    {"LATVIA","Riga"}, {"LIECHTENSTEIN","Vaduz"},
		    {"LITHUANIA","Vilnius"}, {"LUXEMBOURG","Luxembourg"},
		    {"MACEDONIA","Skopje"}, {"MALTA","Valletta"},
		    {"MONACO","Monaco"}, {"MONTENEGRO","Podgorica"},
		    {"THE NETHERLANDS","Amsterdam"}, {"NORWAY","Oslo"},
		    {"POLAND","Warsaw"}, {"PORTUGAL","Lisbon"},
		    {"ROMANIA","Bucharest"}, {"SAN MARINO","San Marino"},
		    {"SERBIA","Belgrade"}, {"SLOVAKIA","Bratislava"},
		    {"SLOVENIA","Ljuijana"}, {"SPAIN","Madrid"},
		    {"SWEDEN","Stockholm"}, {"SWITZERLAND","Berne"},
		    {"UNITED KINGDOM","London"}, {"VATICAN CITY","---"},
		    // North and Central America
		    {"ANTIGUA AND BARBUDA","Saint John’s"},
		    {"BAHAMAS","Nassau"},
		    {"BARBADOS","Bridgetown"}, {"BELIZE","Belmopan"},
		    {"CANADA","Ottawa"}, {"COSTA RICA","San Jose"},
		    {"CUBA","Havana"}, {"DOMINICA","Roseau"},
		    {"DOMINICAN REPUBLIC","Santo Domingo"},
		    {"EL SALVADOR","San Salvador"},
		    {"GRENADA","Saint George’s"},
		    {"GUATEMALA","Guatemala City"},
		    {"HAITI","Port-au-Prince"},
		    {"HONDURAS","Tegucigalpa"}, {"JAMAICA","Kingston"},
		    {"MEXICO","Mexico City"}, {"NICARAGUA","Managua"},
		    {"PANAMA","Panama City"}, {"ST. KITTS","-"},
		    {"NEVIS","Basseterre"}, {"ST. LUCIA","Castries"},
		    {"ST. VINCENT AND THE GRENADINES","Kingstown"},
		    {"UNITED STATES OF AMERICA","Washington, D.C."},
		    // South America
		    {"ARGENTINA","Buenos Aires"},
		    {"BOLIVIA","Sucre (legal)/La Paz(administrative)"},
		    {"BRAZIL","Brasilia"}, {"CHILE","Santiago"},
		    {"COLOMBIA","Bogota"}, {"ECUADOR","Quito"},
		    {"GUYANA","Georgetown"}, {"PARAGUAY","Asuncion"},
		    {"PERU","Lima"}, {"SURINAME","Paramaribo"},
		    {"TRINIDAD AND TOBAGO","Port of Spain"},
		    {"URUGUAY","Montevideo"}, {"VENEZUELA","Caracas"},
	};
	
	private static class FlyWeightMap extends AbstractMap<String, String>{
		
		private static class Entry implements Map.Entry<String, String>{
			int index;
			public Entry(int index){
				this.index = index;
			}
			
			public boolean equals(Object o) {
				return DATA[index][0].equals(o);
			};
			
			@Override
			public String getKey() {
				return DATA[index][0];
			}

			@Override
			public String getValue() {
				return DATA[index][1];
			}

			@Override
			public String setValue(String value) {
				throw new UnsupportedOperationException();
			}
			
			public int hashCode(){
				return DATA[index][0].hashCode();
			}
			
		}

		
		static class EntrySet extends AbstractSet<Map.Entry<String,String>>{
			private int size;
			
			EntrySet(int size) {
				if(size < 0){
					this.size = size;
				}else if(size > DATA.length){
					size = DATA.length;
				}else{
					this.size = size;
				}
			}
			
			
			@Override
			public Iterator<java.util.Map.Entry<String, String>> iterator() {
				return new Iter();
			}

			@Override
			public int size() {
				return size;
			}
			
			private class Iter implements Iterator<Map.Entry<String, String>>{
				private Entry entry = new Entry(-1);
				@Override
				public boolean hasNext() {
					return entry.index < size - 1;
				}

				@Override
				public java.util.Map.Entry<String, String> next() {
					entry.index++;
					return entry;
				}

			}
		}
		
		private static Set<Map.Entry<String, String>> entries = new EntrySet(DATA.length);
		
		@Override
		public Set<java.util.Map.Entry<String, String>> entrySet() {
			return entries;
		}
		
	}
		
		static Map<String, String> selection(final int size){
			return new FlyWeightMap(){
				public Set<java.util.Map.Entry<String, String>> entrySet(){
					return new EntrySet(size);
				}
			};
		}
		
		
		static Map<String, String> map = new FlyWeightMap();
		public static Map<String, String> capitals(){
			return map;
		}
		public static Map<String, String> capitals(int size){
			return selection(size);
		}
		
		
		static List<String> names = new ArrayList<String>(map.keySet());
		public static List<String> names() {
			return names;
		}
		
		public static List<String> names(int size){
			return new ArrayList<String>(selection(size).keySet());
		}
	
	
	public static void main(String[] args) {
		System.out.println(Countries.capitals(10));
		System.out.println(Countries.names(10));
	}
	
}
