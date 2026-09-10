package com.geowatershed.app.data.model

enum class ObservationType(val label: String) {
    SoilErosion("Soil Erosion"),
    Waterlogging("Waterlogging"),
    VegetationLoss("Vegetation Loss"),
    DrainageProblem("Drainage Problem"),
    WaterScarcity("Water Scarcity"),
    ExistingStructure("Existing Structure"),
    AgriculturalCondition("Agricultural Condition"),
    LandDegradation("Land Degradation"),
    Runoff("Runoff"),
    Other("Other"),
}
