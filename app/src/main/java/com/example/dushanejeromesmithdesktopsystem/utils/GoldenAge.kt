package com.example.dushanejeromesmithdesktopsystem.utils

/**
 * An interface representing Golden Age principles, including task management using bit states.
 */
interface GoldenAge {
    /**
     * Begins a task and returns a bit state to represent its status.
     * @param taskName A descriptive name for the task.
     * @return An IBitState representing the task's 2-bit state.
     */
    fun beginTask(taskName: String): IBitState<State2BitValue>

    /**
     * Ends a previously started task.
     * @param taskName The name of the task to end.
     */
    fun endTask(taskName: String)

    /**
     * Starts a service and returns a bit state to represent its status.
     * @param serviceName A descriptive name for the service.
     * @return An IBitState representing the service's 2-bit state.
     */
    fun startService(serviceName: String): IBitState<State2BitValue>

    /**
     * Ends a previously started service.
     * @param serviceName The name of the service to end.
     */
    fun endService(serviceName: String)

    /**
     * Starts an ability and returns a bit state to represent its status.
     * @param abilityName A descriptive name for the ability.
     * @return An IBitState representing the ability's 2-bit state.
     */
    fun startAbility(abilityName: String): IBitState<State2BitValue>

    /**
     * Ends a previously started ability.
     * @param abilityName The name of the ability to end.
     */
    fun endAbility(abilityName: String)
}
