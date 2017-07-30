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
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
  private static final String EXTRA_LAYOUT="layout";
  private static final String EXTRA_TITLE="title";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(getIntent().getIntExtra(EXTRA_LAYOUT, R.layout.activity_main));

    final TextView username=(TextView)findViewById(R.id.username);
    final TextView passphrase=(TextView)findViewById(R.id.passphrase);

    findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
         ResultActivity.login(MainActivity.this, username.getText().toString(),
           passphrase.getText().toString());
      }
    });

    CharSequence title=getIntent().getCharSequenceExtra(EXTRA_TITLE);

    if (title!=null) {
      setTitle(TextUtils.concat(getTitle(), ": ", title));
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.actions, menu);

    return(super.onCreateOptionsMenu(menu));
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()) {
      case R.id.invisible:
        startForLayout(R.layout.activity_invisible, item.getTitle());
        return(true);

/*
      case R.id.gone:
        startForLayout(R.layout.activity_gone, item.getTitle());
        return(true);
*/

      case R.id.tiny:
        startForLayout(R.layout.activity_tiny, item.getTitle());
        return(true);

      case R.id.zero:
        startForLayout(R.layout.activity_zero, item.getTitle());
        return(true);

      case R.id.offscreen:
        startForLayout(R.layout.activity_offscreen, item.getTitle());
        return(true);

      case R.id.behind:
        startForLayout(R.layout.activity_behind, item.getTitle());
        return(true);
    }

    return(super.onOptionsItemSelected(item));
  }

  private void startForLayout(int layoutId, CharSequence title) {
    startActivity(new Intent(this, this.getClass())
      .putExtra(EXTRA_LAYOUT, layoutId)
      .putExtra(EXTRA_TITLE, title));
  }
}
