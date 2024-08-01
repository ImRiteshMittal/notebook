User Stories
============

User Story 1
------------
## Story ##

<p>As a <i>User/Admin</i>,</p>
<p>I can <i>add a note to my notebook with a title and block of text</i></p>
<p>so that <i>I can retrieve them later with the same formatting</i></p>

## Acceptance Criteria ##
<ol>
<li>Should persist after app reboot</li>
<li>Shouldn't accept blank body (Including files with only EOL characters)</li>
<li>Shouldn't accept special characters for titles</li>
</ol>

User Story 2
------------
## Story ##

<p>As an <i>Admin</i>,</p>
<p>I can <i>see all the notes created by all user</i></p>
<p>so that <i>i can keep track of who makes what notes</i></p>

## Acceptance Criteria ##
<ol>
<li>The notes must have the name of the creator on the side</li>
<li>The list should have notes created by all users including admin</li>
<li>If there are no notes, it should give a message "No notes present"</li>
</ol>



User Story 3
------------
## Story ##

<p>As an <i>Admin/User</i>,</p>
<p>I can <i>search for text in notes</i></p>
<p>so that <i>to retrieve notes based on the text</i></p>

## Acceptance Criteria ##
<ol>
<li>Search must not accept blank characters</li>
<li><b>ALL</b> notes with text from search must be displayed</li>
<li>If a user, the search must show only notes created by the user themselves.</li>
</ol>

User Story 4
------------
## Story ##

<p>As an <i>Admin/User</i>,</p>
<p>I can <i>choose an already created note</i></p>
<p>so that <i>the note can be edited</i></p>

## Acceptance Criteria ##
<ol>
<li>The edits must be valid characters(Empty notes must not be saveable)</li>
<li>The title can't be made empty</li>
</ol>

User Story 5
------------
## Story ##

<p>As an <i>User</i>,</p>
<p>I can <i>see all the notes created by me</i></p>

## Acceptance Criteria ##
<ol>
<li>The list should have only notes created by user</li>
<li>The notes must have the name of the creator on the side</li>
<li>If there are no notes, it should be said as such and not given an empty page</li>
</ol>

User Story 6
------------
## Story ##

<p>As an <i>Admin</i>,</p>
<p>I can <i>see all the notes created by all users</i></p>

## Acceptance Criteria ##
<ol>
<li>The notes must have the name of the creator on the side</li>
<li>The list should have only notes created by all users including admin</li>
<li>If there are no notes, it should be said as such and not given an empty page</li>
</ol>

User Story 7
------------
## Story ##

<p>As an <i>Admin</i>,</p>
<p>I can <i>delete a note</i></p>
<p>So that <i>it isn't visible to user</i></p>

## Acceptance Criteria ##
<ol>
<li>The deleted notes shouldn't appear on search</li>
<li>The deleted notes must not appear on admin notes list or the created user's list</li>
</ol>

User Story 7
------------
## Story ##

<p>As an <i>User</i>,</p>
<p>I can <i>delete a note</i></p>
<p>So that <i>it isn't visible to user</i></p>

## Acceptance Criteria ##
<ol>
<li>The deleted notes shouldn't appear on search by the user</li>
<li>The deleted notes must not appear on the created user's list</li>
<li>The deleted notes must be shown as "DELETED" on the created admin's all user list below all the active notes</li>
</ol>
