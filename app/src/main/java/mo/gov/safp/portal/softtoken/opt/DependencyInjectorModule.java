/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mo.gov.safp.portal.softtoken.opt;

import android.content.Context;

import com.google.android.gms.common.internal.Preconditions;


/**
 * Dependency injector module that provides the {@link DependencyInjector} with various injected
 * objects. This module is used in "production" mode, when the app is used as normal and not for
 * automated testing.
 *
 * <p>Once the module has been instantiated, it needs to be provided with a {@link Context}
 * via {@link #initialize(Context)} before it can be used.
 */
public class DependencyInjectorModule {

  private Context mContext;

  private AccountDb mAccountDb;

  /**
   * Gets the {@link Context} passed the instances created by this module.
   */
  public synchronized Context getContext() {
    Preconditions.checkState(mContext != null, "Context not set");
    return mContext;
  }

  public synchronized AccountDb getAccountDb() {
    if (mAccountDb == null) {
      mAccountDb = createAccountDb();
    }
    return mAccountDb;
  }

  protected AccountDb createAccountDb() {
    return new AccountDb(getContext());
  }

  public synchronized void initialize(Context context) {
    mContext = context;
  }

  /**
   * Closes any resources and objects held by this module. The module cannot be used after this
   * operation.
   */
  public synchronized void close() {
    if (mAccountDb != null) {
      mAccountDb.close();
    }

    mContext = null;
    mAccountDb = null;
  }
}
