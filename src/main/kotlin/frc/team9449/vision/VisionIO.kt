package frc.team9449.vision

interface VisionIO {
    class VisionIOInputs : LoggableInputs {
        var connected = false
        var hasPose = false
        var pose = Pose3d()
        var timestampSeconds = 0.0
        var tagCount = 0
        var avgTagDistMeters = 0.0

        override fun toLog(table: LogTable) {
            table.put("Connected", connected)
            table.put("HasPose", hasPose)
            table.put("Pose", pose)
            table.put("TimestampSeconds", timestampSeconds)
            table.put("TagCount", tagCount)
            table.put("AvgTagDistMeters", avgTagDistMeters)
        }

        override fun fromLog(table: LogTable) {
            connected = table.get("Connected", connected)
            hasPose = table.get("HasPose", hasPose)
            pose = table.get("Pose", pose)
            timestampSeconds = table.get("TimestampSeconds", timestampSeconds)
            tagCount = table.get("TagCount", tagCount)
            avgTagDistMeters = table.get("AvgTagDistMeters", avgTagDistMeters)
        }
    }
    override fun disabledInit() {
        RobotContainer.setVisionImuSyncInternal()
    }

    override fun teleopInit() {
        RobotContainer.setVisionImuExternalAssist()
    }
    fun updateInputs(inputs: VisionIOInputs) {}
    fun setImuSyncInternal() {}
    fun setImuExternalAssist() {}
}
