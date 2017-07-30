/***
 Copyright (c) 2017 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.
 */

package com.commonsware.android.autofill.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
  private static final String SEKRIT_PASSPHRASE="password";
  private static final String EXTRA_USERNAME="username";
  private static final String EXTRA_PASSPHRASE="passphrase";

  static boolean login(Context ctxt, String username, String passphrase) {
    if (passphrase.equals(SEKRIT_PASSPHRASE)) {
      Intent i=new Intent(ctxt, ResultActivity.class)
        .putExtra(EXTRA_USERNAME, username)
        .putExtra(EXTRA_PASSPHRASE, passphrase);

      ctxt.startActivity(i);

      return(true);
    }

    return(false);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);

    TextView tv=(TextView)findViewById(R.id.username);

    tv.setText(getIntent().getStringExtra(EXTRA_USERNAME));

    tv=(TextView)findViewById(R.id.passphrase);
    tv.setText(getIntent().getStringExtra(EXTRA_PASSPHRASE));
  }
}
