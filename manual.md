---
layout: page
title: Manual
permalink: /manual/
---

<div>Please select the manual for your version from the list below:</div>

<div>
	<ul>
		{% for post in site.posts %}
  		<li>
    		<a class="post-link" href="{{ post.url | prepend: site.baseurl }}">{{ post.title }}</a>
  		</li>
		{% endfor %}
  </ul>

</div>

