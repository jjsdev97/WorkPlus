/**
 * JQuery Organisation Chart Plugin.
 *
 * Author: Mark Lee
 * Copyright (C)2013-2015 Caprica Software Limited
 * http://www.capricasoftware.co.uk
 *
 * Contributions by: Paul Lautman <paul.lautman at gmail.com>
 *
 * This software is licensed under the Creative Commons Attribution-ShareAlike 3.0 License,
 * see here for license terms:
 *
 *     http://creativecommons.org/licenses/by-sa/3.0
 */
(function($jqchart_) {

    $jqchart_.fn.orgChart = function(options) {
        var opts = $jqchart_.extend({}, $jqchart_.fn.orgChart.defaults, options);

        return this.each(function() {
            var $jqchart_chartSource = $jqchart_(this);
            // Clone the source list hierarchy so levels can be non-destructively removed if needed
            // before creating the chart
            $jqchart_this = $jqchart_chartSource.clone();
            if (opts.levels > -1) {
                $jqchart_this.find("ul").andSelf().filter(function() {return $jqchart_chartSource.parents("ul").length+1 > opts.levels;}).remove();
            }
            // Store the original element
            $jqchart_this.data("chart-source", $jqchart_chartSource);
            // Build the chart...
            var $jqchart_container = $jqchart_("<div class='" + opts.chartClass + "'/>");
            if (opts.interactive) {
                $jqchart_container.addClass("interactive");
            }
            // The chart may be sourced from either a "ul", or an "li" element...
            var $jqchart_root;
            if ($jqchart_this.is("ul")) {
                $jqchart_root = $jqchart_this.find("li:first");
            }
            else if ($jqchart_this.is("li")) {
                $jqchart_root = $jqchart_this;
            }
            if ($jqchart_root) {
                buildNode($jqchart_root, $jqchart_container, 0, 0, opts);
                // Special case for any hyperlink anchor in the chart to prevent the click on the node itself from propagating
                $jqchart_container.find("div.node a").click(function(evt) {
                    evt.stopImmediatePropagation();
                });
                if(opts.replace) {
                    opts.container.empty();
                }
                opts.container.append($jqchart_container);
            }
        });
    };

    $jqchart_.fn.orgChart.defaults = {
        container  : $jqchart_("body"),
        depth      : -1,
        levels     : -1,
        showLevels : -1,
        stack      : false,
        chartClass : "orgChart",
        hoverClass : "hover",
        nodeText   : function($jqchart_node) {return $jqchart_node.clone().children("ul,li").remove().end().html();},
        interactive: false,
        fade       : false,
        speed      : "slow",
        nodeClicked: function($jqchart_node) {},
        copyClasses: true,
        copyData   : true,
        copyStyles : true,
        copyTitle  : true,
        replace    : true
    };

    function buildNode($jqchart_node, $jqchart_appendTo, level, index, opts) {
        var $jqchart_table = $jqchart_("<table cellpadding='0' cellspacing='0' border='0'/>");
        var $jqchart_tbody = $jqchart_("<tbody/>");

        // Make this node...
        var $jqchart_nodeRow = $jqchart_("<tr/>").addClass("nodes");
        var $jqchart_nodeCell = $jqchart_("<td/>").addClass("node").attr("colspan", 2);
        var $jqchart_childNodes = $jqchart_node.children("ul:first").children("li");
        if ($jqchart_childNodes.length > 1) {
            $jqchart_nodeCell.attr("colspan", $jqchart_childNodes.length*2);
        }

        var $jqchart_adjunct = $jqchart_node.children("adjunct").eq(0);
        if ($jqchart_adjunct.length > 0) {
            var $jqchart_adjunctDiv = $jqchart_("<div>").addClass("adjunct node").addClass("level"+level).addClass("node"+index).addClass("level"+level+"-node"+index).append(opts.nodeText($jqchart_adjunct));
            $jqchart_adjunctDiv.appendTo($jqchart_nodeCell);
            var $jqchart_linkDiv = $jqchart_("<div>").addClass("adjunct-link");
            $jqchart_linkDiv.appendTo($jqchart_nodeCell);
            $jqchart_adjunct.remove();
        }

        var $jqchart_heading = $jqchart_("<h2>").html(opts.nodeText($jqchart_node));
        var $jqchart_nodeDiv = $jqchart_("<div>").addClass("node").addClass("level"+level).addClass("node"+index).addClass("level"+level+"-node"+index).append($jqchart_heading);

        // Copy classes from the source list to the chart node
        if (opts.copyClasses) {
            $jqchart_nodeDiv.addClass($jqchart_node.attr("class"));
        }

        // Copy data from the source list to the chart node
        if (opts.copyData) {
            $jqchart_nodeDiv.data($jqchart_node.data());
        }

        // Copy CSS styles from the source list to the chart node
        if (opts.copyStyles) {
            $jqchart_nodeDiv.attr("style", $jqchart_node.attr("style"));
        }

        // Copy the title attribute from the source list to the chart node
        if (opts.copyTitle) {
            $jqchart_nodeDiv.attr("title", $jqchart_node.attr("title"));
        }

        $jqchart_nodeDiv.data("orgchart-level", level).data("orgchart-node", $jqchart_node);

        $jqchart_nodeCell.append($jqchart_nodeDiv);
        $jqchart_nodeRow.append($jqchart_nodeCell);
        $jqchart_tbody.append($jqchart_nodeRow);

        $jqchart_nodeDiv.click(function() {
            var $jqchart_this = $jqchart_(this);
            opts.nodeClicked($jqchart_this.data("orgchart-node"), $jqchart_this);
            if (opts.interactive) {
                var $jqchart_row = $jqchart_this.closest("tr");
                if ($jqchart_row.next("tr").is(":visible")) {
                    if (opts.fade) {
                        $jqchart_row.nextAll("tr").fadeOut(opts.speed);
                    }
                    else {
                        $jqchart_row.nextAll("tr").hide();
                    }
                    $jqchart_this.removeClass("shownChildren").addClass("hiddenChildren");
                }
                else {
                    $jqchart_this.removeClass("hiddenChildren").addClass("shownChildren");
                    if (opts.fade) {
                        $jqchart_row.nextAll("tr").fadeIn(opts.speed);
                    }
                    else {
                        $jqchart_row.nextAll("tr").show();
                    }
                }
            }
        });

        if ($jqchart_childNodes.length > 0) {
            if (opts.depth == -1 || level+1 < opts.depth) {
                var $jqchart_downLineRow = $jqchart_("<tr/>").addClass("lines");
                var $jqchart_downLineCell = $jqchart_("<td/>").attr("colspan", $jqchart_childNodes.length*2);
                $jqchart_downLineRow.append($jqchart_downLineCell);

                var $jqchart_downLineTable = $jqchart_("<table cellpadding='0' cellspacing='0' border='0'>");
                $jqchart_downLineTable.append("<tbody>");
                var $jqchart_downLineLine = $jqchart_("<tr/>").addClass("lines x");
                var $jqchart_downLeft = $jqchart_("<td>").addClass("line left");
                var $jqchart_downRight = $jqchart_("<td>").addClass("line right");
                $jqchart_downLineLine.append($jqchart_downLeft).append($jqchart_downRight);
                $jqchart_downLineTable.children("tbody").append($jqchart_downLineLine);
                $jqchart_downLineCell.append($jqchart_downLineTable);

                $jqchart_tbody.append($jqchart_downLineRow);

                if ($jqchart_childNodes.length > 0) {
                    $jqchart_nodeDiv.addClass("hasChildren");
                    if (opts.showLevels == -1 || level < opts.showLevels-1) {
                        $jqchart_nodeDiv.addClass("shownChildren");
                    }
                    else {
                        $jqchart_nodeDiv.addClass("hiddenChildren");
                    }
                    if (opts.interactive) {
                        $jqchart_nodeDiv.hover(function() {$jqchart_(this).addClass(opts.hoverClass);}, function() {$jqchart_(this).removeClass(opts.hoverClass)});
                    }
                }

                // Recursively make child nodes...
                var $jqchart_linesRow = $jqchart_("<tr/>").addClass("lines v");
                $jqchart_childNodes.each(function() {
                    var $jqchart_left = $jqchart_("<td/>").addClass("line left top");
                    var $jqchart_right = $jqchart_("<td/>").addClass("line right top");
                    $jqchart_linesRow.append($jqchart_left).append($jqchart_right);
                });
                $jqchart_linesRow.find("td:first").removeClass("top");
                $jqchart_linesRow.find("td:last").removeClass("top");
                $jqchart_tbody.append($jqchart_linesRow);
                var $jqchart_childNodesRow = $jqchart_("<tr/>");
                $jqchart_childNodes.each(function(index) {
                    var $jqchart_td = $jqchart_("<td/>");
                    $jqchart_td.attr("colspan", 2);
                    buildNode($jqchart_(this), $jqchart_td, level+1, index, opts);
                    $jqchart_childNodesRow.append($jqchart_td);
                });
                $jqchart_tbody.append($jqchart_childNodesRow);
            }
            else if (opts.stack) {
                var $jqchart_stackNodes = $jqchart_childNodes.clone();
                var $jqchart_list = $jqchart_("<ul class='stack'>").append($jqchart_stackNodes).addClass("level"+level).addClass("node"+index).addClass("level"+level+"-node"+index);
                var $jqchart_stackContainer = $jqchart_("<div class='stack-container'>").append($jqchart_list);
                $jqchart_nodeDiv.after($jqchart_stackContainer);
            }
        }

        if (opts.showLevels > -1 && level >= opts.showLevels-1) {
            $jqchart_nodeRow.nextAll("tr").hide();
        }

        $jqchart_table.append($jqchart_tbody);
        $jqchart_appendTo.append($jqchart_table);
    };

})(jQuery);
