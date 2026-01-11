package com.example.dushanejeromesmithdesktopsystem.utils

/**
 * An interface for providing program metadata.
 * This is based on the concepts defined in the project's desktop.ini file.
 * It inherits from GoldenAge to include task management capabilities.
 */
interface ProgramMetadata : GoldenAge {
    fun getCorePrinciples(): List<String>
    fun getInjectionPoints(): Map<String, String>
    fun getAssets(): List<String>
    fun getAcademics(): List<String>
    fun getAbilities(): List<String>

    /**
     * Gets a list of services that can have abilities injected.
     */
    fun getServices(): List<String>
}
