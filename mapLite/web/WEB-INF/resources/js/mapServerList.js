var mapservers = [
    {
        url:"https://gis.water.ca.gov/arcgis/rest/services/Public/Boundaries_Map/MapServer/",
        mid:0
    }
    ,{
        url: "http://ferix.water.ca.gov/arcgis/rest/services/precipitation/MapServer/"
        ,mid: 1
    }
    ,{
        url: "http://webdev02.geiconsultants.com:8399/arcgis/rest/services/groundwater/wisestation/MapServer/"
        ,mid: 2
    }
    ,{
        url: "https://gis.water.ca.gov/arcgis/rest/services/Public/GIC_Boundaries/MapServer/"
        ,mid: 3
    }
//    ,{
//        url: "https://gis.water.ca.gov/arcgis/rest/services/Public/GIC_Elevation_Contours/MapServer/"
//        ,mid: 4
//    }    
];

var layerTabs = [
    {
        "title": "Jurisdiction",
        "id": 0
    },
    {
       "title": "Hydrology",
        "id": 1 
    },
    {
        "title": "Projects",
        "id": 2
    },
    {
        "title": "Groundwater",
        "id": 3
    }    
    
]


var layers =[
    {
        "mid": 0,
        "lid":  27,
        "tab": 0,
        "name": "Water Agencies",
        "legendPath": []
    },
    {
        "mid": 0,
        "lid":  0,
        "tab": 0,
        "name": "County Boundaries",
        "legendPath": [
            {
                "label":"",
                "path":"countyboundaries.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 32  ,
        "tab": 0,
        "name": "California Senate Districts",
        "legendPath": [
            {
                "label":"",
                "path":"CaliforniaSenateDistricts.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid":  33,
        "tab": 0,
        "name": "California Assembly Districts",
        "legendPath": [
            {
                "label":"",
                "path":"CaliforniaAssemblyDistricts.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid":15,
        "tab": 0,
        "name": "Reclamation Districts",
        "legendPath": [
            {
                "label":"",
                "path":"ReclimationDistricts.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 16,
        "tab": 0,
        "name": "CA State Park Lands",
        "legendPath": [
            {
                "label":"",
                "path":"StateParkLands.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 23,
        "tab": 0,
        "name": "Federal Lands",
        "legendPath": [
            {
                "label":"NPS",
                "path":"FederalLandsNPS.png"
            },
            {
                "label":"FWS",
                "path":"FederalLandsFWS.png"
            },
            {
                "label":"FS",
                "path":"FederalLandsFS.png"
            },
            {
                "label":"DOD",
                "path":"FederalLandsDOD.png"
            },
            {
                "label":"BOR",
                "path":"FederalLandsBOR.png"
            },
            {
                "label":"BLM",
                "path":"FederalLandsBLM.png"
            },
            {
                "label":"BIA",
                "path":"FederalLandsBIA.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid":  1,
        "tab": 0,
        "name": "Region Office Service Areas",
        "legendPath": [
            {
                "label":"",
                "path":"RegionOfficeServiceAreas.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 5,
        "tab": 0,
        "name": "Regional Water Quality Control Board Boundaries",
        "legendPath": [
            {
                "label":"",
                "path":"WaterControlBoardBoundaries.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid":  7,
        "tab": 0,
        "name": "Location of a Historic or Landless Tribe",
        "legendPath": [
            {
                "label":"",
                "path":"LocationOfTribe.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid":  8,
        "tab": 0,
        "name": "Tribal Trust Land Office Out of State",
        "legendPath": [
            {
                "label":"",
                "path":"TribalOutOfState.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 9,
        "tab": 0,
        "name": "Tribal Trust Land in a Public Domain Allotment",
        "legendPath": [
            {
                "label":"",
                "path":"TribalDomainAllotment.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid":  10,
        "tab": 0,
        "name":"Tribal Trust Land held by US Government",
        "legendPath": [
            {
                "label":"",
                "path":"TribalHeldByUS.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 24,
        "tab": 0,
        "name": "Disadvantaged Community Block Groups",
        "legendPath": [
            {
                "label":"",
                "path":"DisadvantagedBlockGroups.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 25,
        "tab": 0,
        "name": "Disadvantaged Community Tracts",
        "legendPath": [
            {
                "label":"",
                "path":"DisadvantagedTracts.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 26,
        "tab": 0,
        "name": "Disadvantaged Community Places",
        "legendPath": [
            {
                "label":"",
                "path":"DisadvantagedPlaces.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 2,
        "tab": 1,
        "name": "Hydrologic Regions",
        "legendPath": [
            {
                "label":"",
                "path":"HydrologicRegions.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 31,
        "tab": 1,
        "name": "Watersheds",
        "legendPath": [
        ]
    },
    {
        "mid": 0,
        "lid": 14,
        "tab": 1,
        "name": "Regional Flood Planning Boundaries",
        "legendPath": [
            {
                "label":"Area of Local Interest",
                "path":"RegionalFloodAreaOfInterest.png"
            },
            {
                "label":"Regional Flood Planning Area",
                "path":"RegionalFloodPlanningArea.png"
            }  
        ]
    },
    {
        "mid": 0,
        "lid": 18,
        "tab": 1,
        "name": "Adjudicated Groundwater Basins",
        "legendPath":[
            {
                "label":"",
                "path":"AdjudicatedRegion.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 28,
        "tab": 1,
        "name": "Bulletin 118 Groundwater Basins",
        "legendPath": [
            {
                "label":"",
                "path":"Bulletin118.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 29,
        "tab": 1,
        "name": "Detailed Analysis Units",
        "legendPath": [
        ]
    },
    {
        "mid": 1,
        "lid": 1,
        "tab": 1,
        "name": "Precipitation (Daily)",
        "legendPath": [
            {
                "label":""
                ,"path":"PrecipitationDaily.png"
            }            
        ]
    },    
    {
        "mid": 2,
        "lid": 0,
        "tab": 1,
        "name": "Full Natural Flow",
        "links": [
            {
                "column":"ID"
                ,"link":"<a href='http://cdec.water.ca.gov/cgi-progs/queryMonthly?<val>' target='blank'><val></a>"
            }
        ],        
        "legendPath": [
            {
                "label":""
                ,"path":"naturalflow.png"
            }            
        ]
    },  
    {
        "mid": 2,
        "lid": 1,
        "tab": 1,
        "name": "Stream",
        "links": [
            {
                "column":"ID"
                ,"link":"<a href='http://cdec.water.ca.gov/cgi-progs/queryDaily?<val>' target='blank'><val></a>"
            }
        ],
        "legendPath": [
            {
                "label":""
                ,"path":"stream.png"
            }            
        ]
    },      
    {
        "mid": 0,
        "lid": 3,
        "tab": 2,
        "name": "Prop 1 Funding Areas",
        "legendPath": [
            {
                "label":"",
                "path":"Prop1FundingAreas.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 4,
        "tab": 2,
        "name": "Prop 84 Funding Areas",
        "legendPath": [
            {
                "label":"",
                "path":"Prop84FundingAreas.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 15,
        "tab": 2,
        "name": "CASGEM Designated Monitoring Entities as of 03-04-15",
        "legendPath":[
            {
                "label":"",
                "path":"CASGEMMonitoringEntities.png"
            }
        ]
    },
    {
        "mid": 0,
        "lid": 16,
        "tab": 2,
        "name": "CASGEM Groundwater Basin Prioritization",
        "legendPath": [
            {
                "label":"High",
                "path":"CASGEMPriorityHigh.png"
            },
            {
                "label":"Medium",
                "path":"CASGEMPriorityMedium.png"
            },
            {
                "label":"Low",
                "path":"CASGEMPriorityLow.png"
            },
            {
                "label":"Very Low",
                "path":"CASGEMPriorityVeryLow.png"
            }
        ]
    }
    ,{
        "mid": 0,
        "lid": 17,
        "tab": 2,
        "name": "Groundwater Management Plans",
        "legendPath": [
            {
                "label":"AB 359",
                "path":"GroundwaterPlanAB359.png"
            },
            {
                "label":"SB 1988",
                "path":"GroundwaterPlanSB1938.png"
            },
            {
                "label":"AB 3030",
                "path":"GroundwaterPlanAB3030.png"
            }
        ]
    }
    ,{
        "mid": 0,
        "lid": 18,
        "tab":2,
        "name": "IRWM Regions",
        "legendPath": [
        ]
    }
    ,{
        "mid": 0,
        "lid": 26,
        "tab": 2,
        "name": "Water Plan Planning Areas",
        "legendPath": [
        ]
    }
    ,{
        "mid": 3,
        "lid":  2,
        "tab": 3,
        "name": "Groundwater Management Plans",
        "legendPath": [
            {
                "label":"AB 359",
                "path":"GroundwaterPlanAB359.png"
            },
            {
                "label":"SB 1988",
                "path":"GroundwaterPlanSB1938.png"
            },
            {
                "label":"AB 3030",
                "path":"GroundwaterPlanAB3030.png"
            }
        ]
    }  
    ,{
        "mid": 3,
        "lid":  3,
        "tab": 3,
        "name": "CASGEM Groundwater Basin Prioritization",
        "legendPath": [
            {
                "label":"Very Low",
                "path":"CASGEMPriorityVeryLow.png"
            },            
            {
                "label":"Low",
                "path":"CASGEMPriorityLow.png"
            },
            {
                "label":"Medium",
                "path":"CASGEMPriorityMedium.png"
            },
            {
                "label":"High",
                "path":"CASGEMPriorityHigh.png"
            }            
        ]
    }  
//    ,{
//        "mid": 4,
//        "lid":  0,
//        "tab": 3,
//        "name": "2012 Spring Groundwater Level",
//        "legendPath": [
//            {
//                "label":"Sea Level",
//                "path":"S2012Sea.png"
//            },            
//            {
//                "label":"Primary Contour",
//                "path":"S2012Primary.png"
//            },
//            {
//                "label":"Secondary Contour",
//                "path":"S2012Secondary.png"
//            } 
//        ]
//    }     
//    ,{
//        "mid": 4,
//        "lid":  1,
//        "tab": 3,
//        "name": "2013 Spring Groundwater Level",
//        "legendPath": [
//            {
//                "label":"Sea Level",
//                "path":"S2013Sea.png"
//            },            
//            {
//                "label":"Primary Contour",
//                "path":"S2013Primary.png"
//            },
//            {
//                "label":"Secondary Contour",
//                "path":"S2013Secondary.png"
//            } 
//        ]
//    }  
//    ,{
//        "mid": 4,
//        "lid":  2,
//        "tab": 3,
//        "name": "2014 Spring Groundwater Level",
//        "legendPath": [
//            {
//                "label":"Sea Level",
//                "path":"S2014Sea.png"
//            },            
//            {
//                "label":"Primary Contour",
//                "path":"S2014Primary.png"
//            },
//            {
//                "label":"Secondary Contour",
//                "path":"S2014Secondary.png"
//            } 
//        ]
//    }     
];

