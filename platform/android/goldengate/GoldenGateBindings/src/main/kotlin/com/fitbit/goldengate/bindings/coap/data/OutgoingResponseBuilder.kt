// Copyright 2017-2020 Fitbit, Inc
// SPDX-License-Identifier: Apache-2.0

package com.fitbit.goldengate.bindings.coap.data

/**
 * Concrete class of MessageBuilder for building [OutgoingResponse]
 */
class OutgoingResponseBuilder : BaseOutgoingMessageBuilder<OutgoingResponse>() {

    internal var responseCode = ResponseCode.created

    internal var autogenerateBlockwiseConfig = false

    /**
     * Set the [ResponseCode] to build a response with (e.g. 2.01, 4.04)
     *
     * @param code the [ResponseCode] this builder will build an [OutgoingResponse] with.
     */
    fun responseCode(code: ResponseCode): OutgoingResponseBuilder {
        responseCode = code
        return this
    }

    /**
     * Flag to use auto-configured block1 and block2 options and response code in the CoAP response
     * provided by xp lib. If handler needs to respond to non-blockwise request or reply with error
     * code, then this flag should not be set.
     *
     * If autogenerateBlockwiseConfig is set, XP lib will take care of the blockwise options
     * (BLOCK1 and BLOCK2) and the corresponding response code for you.
     *
     * @param value true if the autogenerated blockwise CoAP response is used
     */
    fun autogenerateBlockwiseConfig(value: Boolean): OutgoingResponseBuilder {
        autogenerateBlockwiseConfig = value
        return this
    }

    override fun build(): OutgoingResponse {
        return object : OutgoingResponse {
            override val responseCode: ResponseCode
                get() = this@OutgoingResponseBuilder.responseCode

            override val body: OutgoingBody
                get() = this@OutgoingResponseBuilder.body

            override val options: Options
                get() = this@OutgoingResponseBuilder.options

            override val autogenerateBlockwiseConfig: Boolean
                get() = this@OutgoingResponseBuilder.autogenerateBlockwiseConfig
        }
    }
}
