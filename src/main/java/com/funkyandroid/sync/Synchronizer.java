/*
 * Copyright (c) 2014 Funky Android Ltd.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.funkyandroid.sync;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Synchronizer {

    private final Destination mDestination;

    public Synchronizer(final Destination destination) {
        mDestination = destination;
    }

    public void synchronize(StorageObject sourceObject) {
        if(!mDestination.needsUpdating(sourceObject)) {
            System.out.println("Skipping "+sourceObject.getName()+" (Up to date)");
            return;
        }

        try {
            System.out.print("Updating " + sourceObject.getName());
            mDestination.storeCopyOf(sourceObject);
            System.out.println("... Done");
        } catch(IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Synchronisation of "+sourceObject.getName()+" skipped.", e);
        }
    }
}
