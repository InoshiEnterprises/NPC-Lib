/*
 * This file is part of npc-lib, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2022 Julian M., Pasqual K. and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.juliarn.npclib.api.protocol.meta;

import com.github.juliarn.npclib.api.protocol.enums.EntityPose;

interface DefaultEntityMetadata {

  // https://wiki.vg/Entity_metadata#Entity - see index 0 and 6
  EntityMetadataFactory<Boolean, Byte> SNEAKING = EntityMetadataFactory.<Boolean, Byte>metaFactoryBuilder()
    .baseIndex(0)
    .type(Byte.class)
    .inputConverter(value -> (byte) (value ? 0x02 : 0x00))
    .addRelatedMetadata(EntityMetadataFactory.<Boolean, Object>metaFactoryBuilder()
      .baseIndex(6)
      .type(EntityPose.class)
      .inputConverter(value -> value ? EntityPose.CROUCHING : EntityPose.STANDING)
      .availabilityChecker(versionAccessor -> versionAccessor.atLeast(1, 14, 0))
      .build())
    .build();

  // https://wiki.vg/Entity_metadata#Player
  EntityMetadataFactory<Boolean, Byte> SKIN_LAYERS = EntityMetadataFactory.<Boolean, Byte>metaFactoryBuilder()
    .baseIndex(10)
    .type(Byte.class)
    .indexShiftVersions(9, 9, 10, 14, 14, 15, 17)
    .inputConverter(value -> (byte) (value ? 0xff : 0x00))
    .build();
}